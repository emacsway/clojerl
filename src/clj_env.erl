-module(clj_env).

-export([
         default/0,
         push_expr/2,
         pop_expr/1,
         in_ns/2,
         add_ns/2,
         current_ns/1,
         current_ns/2,
         update_ns/3,
         get_ns/2,
         get_local/2,
         update_var/2
        ]).

-type env() :: #{namespaces => [],
                 exprs => [],
                 current_ns => 'clojerl.Symbol':type(),
                 locals => #{'clojerl.Symbol':type() => any()}}.

-export_type([env/0]).

-spec default() -> env().
default() ->
  UserSym = clj_core:symbol(<<"user">>),
  UserNs = clj_namespace:new(UserSym),
  #{namespaces => #{UserSym => UserNs},
    exprs      => [],
    current_ns => UserSym,
    locals     => #{}}.

-spec push_expr(env(), erl_syntax:syntaxTree()) -> env().
push_expr(Env = #{exprs := Exprs}, Expr) ->
  Env#{exprs => [Expr | Exprs]};
push_expr(Env, Expr) ->
  Env#{exprs => [Expr]}.

-spec pop_expr(env()) -> env().
pop_expr(Env = #{exprs := [H | Exprs]}) ->
  {H, Env#{exprs => Exprs}};
pop_expr(Env) ->
  {undefined, Env}.

-spec in_ns(env(), 'clojerl.Symbol':type()) -> env().
in_ns(Env, NsSym) ->
  case get_ns(Env, NsSym) of
    undefined ->
      Ns = clj_namespace:new(NsSym),
      current_ns(NsSym, add_ns(Ns, Env));
    _ ->
      current_ns(NsSym, Env)
  end.

-spec add_ns(clj_namespace:namespace(), env()) -> env().
add_ns(Ns, Env = #{namespaces := Namespaces}) ->
  NsSym = clj_namespace:name(Ns),
  case get_ns(Env, NsSym) of
    undefined ->
      NewNamespaces = maps:put(NsSym, Ns, Namespaces),
      Env#{namespaces => NewNamespaces};
    _ ->
      Env
  end.

-spec current_ns(env()) -> clj_namespace:namespace().
current_ns(#{current_ns := CurrentNs}) ->
  CurrentNs.

-spec current_ns('clojerl.Symbol':type(), env()) -> env().
current_ns(CurrentNs, Env) ->
  case get_ns(Env, CurrentNs) of
    undefined ->
      throw(<<"The specified namespace does not exist">>);
    _ ->
      Env#{current_ns => CurrentNs}
  end.

-spec update_ns('clojerl.Symbol':type(), function(), env()) -> clj_namespace:namespace().
update_ns(Name, Fun, Env = #{namespaces := Nss}) ->
  case maps:get(Name, Nss, undefined) of
    undefined ->
      Env;
    Ns ->
      NewNs = Fun(Ns),
      NewNss = maps:put(Name, NewNs, Nss),
      Env#{namespaces => NewNss}
  end.

-spec get_ns(env(), 'clojerl.Symbol':type()) -> clj_namespace:namespace().
get_ns(_Env = #{namespaces := Nss}, SymNs) ->
  maps:get(SymNs, Nss, undefined).

-spec get_local(env(), 'clojerl.Symbol':type()) -> clj_namespace:namespace().
get_local(_Env = #{locals := Locals}, Sym) ->
  maps:get(Sym, Locals, undefined).

-spec update_var('clojerl.Var':type(), env()) -> clj_namespace:namespace().
update_var(Var, Env) ->
  VarNsSym = 'clojerl.Var':namespace(Var),
  Fun = fun(Ns) -> clj_namespace:update_var(Ns, Var) end,
  update_ns(VarNsSym, Fun, Env).
