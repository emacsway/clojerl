-module('clojerl.IColl').

-export([cons/2, empty/1]).

-type type() :: any().

-callback 'clojerl.IColl.cons'(IColl :: type(), Item :: any()) -> type().
-callback 'clojerl.IColl.empty'(IColl :: type()) -> any().

-spec cons(type(), any()) -> type().
cons(Coll, Item) ->
  'clojerl.protocol':resolve(?MODULE, cons, [Coll, Item]).

-spec empty(type()) -> any().
empty(Coll) ->
  'clojerl.protocol':resolve(?MODULE, empty, [Coll]).