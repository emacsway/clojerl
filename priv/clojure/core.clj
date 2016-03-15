(ns clojure.core)

(def ^:dynamic *ns* nil)

(def
  ^{:arglists '([& items])
    :doc "Creates a new list containing the items."
    :added "1.0"}
  list (fn* [& items] (clojerl.List/new.e items)))

(def
  ^{:arglists '([x seq])
    :doc "Returns a new seq where x is the first element and seq is
    the rest."
    :added "1.0"
    :static true}
  cons (fn* [x s] (clj_core/cons.e x s)))

(def
  ^{:macro true
    :added "1.0"}
  let (fn* let [_&form _&env & decl] (cons 'let* decl)))

(def
  ^{:macro true
    :added "1.0"}
  loop (fn* loop [_&form _&env & decl] (cons 'loop* decl)))

(def
  ^{:macro true
    :added "1.0"}
  fn (fn* fn [&form _&env & decl]
         (let [x (cons 'fn* (clojerl.List/new.e decl))]
           (clj_core/with_meta.e x (clj_core/meta.e &form)))))

(def
  ^{:arglists '([coll])
    :doc "Returns the first item in the collection. Calls seq on its
    argument. If coll is nil, returns nil."
    :added "1.0"
    :static true}
  first (fn ^:static first [coll] (clj_core/first.e coll)))

(def
  ^{:arglists '([coll])
    :tag cojerl.ISeq
    :doc "Returns a seq of the items after the first. Calls seq on its
  argument.  If there are no more items, returns nil."
    :added "1.0"
    :static true}
  next (fn ^:static next [x] (clj_core/next.e x)))

(def
  ^{:arglists '([coll])
    :tag clojerl.ISeq
    :doc "Returns a possibly empty seq of the items after the first. Calls seq on its
  argument."
    :added "1.0"
    :static true}
  rest (fn ^:static rest [x] (clj_core/rest.e x)))

(def
  ^{:arglists '([coll x] [coll x & xs])
    :doc "conj[oin]. Returns a new collection with the xs
    'added'. (conj nil item) returns (item).  The 'addition' may
    happen at different 'places' depending on the concrete type."
    :added "1.0"
    :static true}
  conj (fn ^:static conj
         ([] [])
         ([coll] coll)
         ([coll x] (clj_core/conj.e coll x))
         ([coll x & xs]
          (if xs
            (recur (conj coll x) (first xs) (next xs))
            (conj coll x)))))

(def
  ^{:doc "Same as (first (next x))"
    :arglists '([x])
    :added "1.0"
    :static true}
  second (fn ^:static second [x] (first (next x))))

(def
  ^{:doc "Same as (first (first x))"
    :arglists '([x])
    :added "1.0"
    :static true}
  ffirst (fn ^:static ffirst [x] (first (first x))))

(def
  ^{:doc "Same as (next (first x))"
    :arglists '([x])
    :added "1.0"
    :static true}
  nfirst (fn ^:static nfirst [x] (next (first x))))

(def
  ^{:doc "Same as (first (next x))"
    :arglists '([x])
    :added "1.0"
    :static true}
  fnext (fn ^:static fnext [x] (first (next x))))

(def
  ^{:doc "Same as (next (next x))"
    :arglists '([x])
    :added "1.0"
    :static true}
  nnext (fn ^:static nnext [x] (next (next x))))

(def
  ^{:arglists '([coll])
    :doc "Returns a seq on the collection. If the collection is
    empty, returns nil.  (seq nil) returns nil. seq also works on
    Strings, native Java arrays (of reference types) and any objects
    that implement Iterable. Note that seqs cache values, thus seq
    should not be used on any Iterable whose iterator repeatedly
    returns the same mutable object."
    :tag clojure.lang.ISeq
    :added "1.0"
    :static true}
  seq (fn ^:static seq [coll] (clj_core/seq.e coll)))

(def
  ^{:arglists '([p x])
    :doc "Evaluates x and tests if it extends the protocol
    p. Returns true or false"
    :added "1.0"}
  extends? (fn extends? [p x]
             (clj_core/extends?.e p (clj_core/type.e x))))

(def
  ^{:arglists '([t x])
    :doc "Evaluates x and tests if it is of type t.
    Returns true or false"
    :added "1.0"}
  instance? (fn instance? [t x] (erlang/==.e t (clj_core/type.e x))))

(def
  ^{:arglists '([x])
    :doc "Return true if x implements ISeq"
    :added "1.0"
    :static true}
  seq? (fn ^:static seq? [x] (extends? :clojerl.ISeq x)))

(def
  ^{:arglists '([x])
    :doc "Return true if x implements ISeq"
    :added "1.0"
    :static true}
  lazy-seq? (fn ^:static seq? [x] (instance? :clojerl.LazySeq x)))

(def
  ^{:arglists '([x])
    :doc "Return true if x implements IMeta"
    :added "1.0"
    :static true}
  meta? (fn ^:static meta? [x] (extends? :clojerl.IMeta x)))

#_(def
    ^{:arglists '([x])
      :doc "Return true if x is a Character"
      :added "1.0"
      :static true}
    char? (fn ^:static char? [x] (extends? Character x)))

(def
  ^{:arglists '([x])
    :doc "Return true if x is a String"
    :added "1.0"
    :static true}
  string? (fn ^:static string? [x] (instance? :clojerl.String x)))

(def
  ^{:arglists '([x])
    :doc "Return true if x implements IMap"
    :added "1.0"
    :static true}
  map? (fn ^:static map? [x] (extends? :clojerl.IMap x)))

(def
  ^{:arglists '([x])
    :doc "Return true if x is a Vector"
    :added "1.0"
    :static true}
  vector? (fn ^:static vector? [x] (instance? :clojerl.Vector x)))

(def
  ^{:arglists '([x])
    :doc "Return true if x is a Symbol"
    :added "1.0"
    :static true}
  symbol? (fn ^:static symbol? [x] (instance? :clojerl.Symbol x)))

(def
  ^{:arglists '([map key val] [map key val & kvs])
    :doc "assoc[iate]. When applied to a map, returns a new map of the
    same (hashed/sorted) type, that contains the mapping of key(s) to
    val(s). When applied to a vector, returns a new vector that
    contains val at index. Note - index must be <= (count vector)."
    :added "1.0"
    :static true}
  assoc
  (fn ^:static assoc
    ([map key val] (clj_core/assoc.e map key val))
    ([map key val & kvs]
     (let [ret (assoc map key val)]
       (if kvs
         (if (next kvs)
           (recur ret (first kvs) (second kvs) (nnext kvs))
           (throw "assoc expects even number of arguments after map/vector, found odd number"))
         ret)))))

;;;;;;;;;;;;;;;;; metadata ;;;;;;;;;;;;;;;;;;;;;;;;;;;
(def
  ^{:arglists '([obj])
    :doc "Returns the metadata of obj, returns nil if there is no metadata."
    :added "1.0"
    :static true}
  meta (fn ^:static meta [x]
         (if (meta? x)
           (clj_core/meta.e x))))

(def
  ^{:arglists '([^clojure.lang.IObj obj m])
    :doc "Returns an object of the same type and value as obj, with
    map m as its metadata."
    :added "1.0"
    :static true}
  with-meta (fn ^:static with-meta [x m]
              (clj_core/with_meta.e x m)))

(def ^{:private true :dynamic true}
  assert-valid-fdecl
  (fn [fdecl]
    (throw "unimplemented")))

(def
  ^{:private true}
  sigs
  (fn [fdecl]
    (throw "unimplemented")
    (assert-valid-fdecl fdecl)
    (let [asig
          (fn [fdecl]
            (let [arglist (first fdecl)
                                        ;elide implicit macro args
                  arglist (if (clj_utils/equals.e '&form (first arglist))
                            (clj_core/subvec.e arglist 2 (clj_core/count.e arglist))
                            arglist)
                  body (next fdecl)]
              (if (map? (first body))
                (if (next body)
                  (with-meta arglist (conj (if (meta arglist) (meta arglist) {}) (first body)))
                  arglist)
                arglist)))]
      (if (seq? (first fdecl))
        (loop [ret [] fdecls fdecl]
          (if fdecls
            (recur (conj ret (asig (first fdecls))) (next fdecls))
            (seq ret)))
        (list (asig fdecl))))))

(def
  ^{:arglists '([coll])
    :doc "Return the last item in coll, in linear time"
    :added "1.0"
    :static true}
  last (fn ^:static last [s]
         (if (next s)
           (recur (next s))
           (first s))))

(def
  ^{:arglists '([coll])
    :doc "Return a seq of all but the last item in coll, in linear time"
    :added "1.0"
    :static true}
  butlast (fn ^:static butlast [s]
            (loop [ret [] s s]
              (if (next s)
                (recur (conj ret (first s)) (next s))
                (seq ret)))))

(def ^:macro defn
  (fn* [form env name & fdecl]
       (if (symbol? name)
         nil
         (throw "First argument to defn must be a symbol"))
       (let [m     (if (string? (first fdecl))
                     {:doc (first fdecl)}
                     {})
             fdecl (if (string? (first fdecl))
                     (next fdecl)
                     fdecl)
             m     (if (map? (first fdecl))
                     (conj m (first fdecl))
                     m)
             fdecl (if (map? (first fdecl))
                     (next fdecl)
                     fdecl)
             fdecl (if (vector? (first fdecl))
                     (list fdecl)
                     fdecl)
             m     (if (map? (last fdecl))
                     (conj m (last fdecl))
                     m)
             fdecl (if (map? (last fdecl))
                     (butlast fdecl)
                     fdecl)
             ;; m     (conj {:arglists (list 'quote (sigs fdecl))} m)
             m     (conj (if (meta name) (meta name) {}) m)]
         (list 'def (with-meta name m)
               (cons 'clojure.core/fn ;; can't use syntax-quote here yet because
                     ;; we haven't defined all the necessary functions
                     (clojerl.List/new.e (seq fdecl)))))))

(defn to-tuple
  "Returns a tuple of Objects containing the contents of coll, which
  can be any Collection.  Maps to erlang:list_to_tuple/2."
  {:tag "clojerl.erlang.Tuple"
   :added "1.0"
   :static true}
  [coll] (erlang/list_to_tuple.e (seq coll)))

(defn vector
  "Creates a new vector containing the args."
  {:added "1.0"
   :static true}
  ([] [])
  ([a] [a])
  ([a b] [a b])
  ([a b c] [a b c])
  ([a b c d] [a b c d])
  ([a b c d & args]
   (clj_core/vector.e (cons a (cons b (cons c (cons d args)))))))

(defn vec
  "Creates a new vector containing the contents of coll. Java arrays
  will be aliased and should not be modified."
  {:added "1.0"
   :static true}
  ([coll]
   (if (vector? coll)
     (if (extends? :clojerl.IMeta coll)
       (with-meta coll nil)
       (clj_core/vector.e coll))
     (clj_core/vector.e coll))))

(defn hash-map
  "keyval => key val
  Returns a new hash map with supplied mappings.  If any keys are
  equal, they are handled as if by repeated uses of assoc."
  {:added "1.0"
   :static true}
  ([] {})
  ([& keyvals]
   (clj_core/hash_map.e keyvals)))

(defn hash-set
  "Returns a new hash set with supplied keys.  Any equal keys are
  handled as if by repeated uses of conj."
  {:added "1.0"
   :static true}
  ([] #{})
  ([& keys]
   (clj_core/hash_set.e keys)))

(defn sorted-map
  "keyval => key val
  Returns a new sorted map with supplied mappings.  If any keys are
  equal, they are handled as if by repeated uses of assoc."
  {:added "1.0"
   :static true}
  ([& keyvals]
   (throw "unsupported")
   #_(clojure.lang.PersistentTreeMap/create keyvals)))

(defn sorted-map-by
  "keyval => key val
  Returns a new sorted map with supplied mappings, using the supplied
  comparator.  If any keys are equal, they are handled as if by
  repeated uses of assoc."
  {:added "1.0"
   :static true}
  ([comparator & keyvals]
   (throw "unsupported")
   #_(clojure.lang.PersistentTreeMap/create comparator keyvals)))

(defn sorted-set
  "Returns a new sorted set with supplied keys.  Any equal keys are
  handled as if by repeated uses of conj."
  {:added "1.0"
   :static true}
  ([& keys]
   (throw "unsupported")
   #_(clojure.lang.PersistentTreeSet/create keys)))

(defn sorted-set-by
  "Returns a new sorted set with supplied keys, using the supplied
  comparator.  Any equal keys are handled as if by repeated uses of
  conj."
  {:added "1.1"
   :static true}
  ([comparator & keys]
   (throw "unsupported")
   #_(clojure.lang.PersistentTreeSet/create comparator keys)))

;;;;;;;;;;;;;;;;;;;;

(defn nil?
  "Returns true if x is nil, false otherwise."
  {:tag Boolean
   :added "1.0"
   :static true
   :inline (fn [x] (list 'erlang/=:=.e x nil))}
  [x] (erlang/=:=.e x nil))

(def
  ^{:macro true
    :doc "Like defn, but the resulting function name is declared as a
  macro and will be used as a macro by the compiler when it is
  called."
    :arglists '([name doc-string? attr-map? [params*] body]
                [name doc-string? attr-map? ([params*] body)+ attr-map?])
    :added "1.0"}
  defmacro (fn [&form &env name & args]
             (let [name   (with-meta name (conj (meta name) {:macro true}))
                   prefix (loop [p (list name) args args]
                            (let [f (first args)]
                              (if (string? f)
                                (recur (cons f p) (next args))
                                (if (map? f)
                                  (recur (cons f p) (next args))
                                  p))))
                   fdecl (loop [fd args]
                           (if (string? (first fd))
                             (recur (next fd))
                             (if (map? (first fd))
                               (recur (next fd))
                               fd)))
                   fdecl (if (vector? (first fdecl))
                           (list fdecl)
                           fdecl)
                   add-implicit-args (fn [fd]
                                       (let [args (first fd)]
                                         (cons (vec (cons '&form (cons '&env args)))
                                               (next fd))))
                   add-args (fn [acc ds]
                              (if (nil? ds)
                                acc
                                (let [d (first ds)]
                                  (if (map? d)
                                    (conj acc d)
                                    (recur (conj acc (add-implicit-args d))
                                           (next ds))))))
                   fdecl (seq (add-args [] fdecl))
                   decl (loop [p prefix d fdecl]
                          (if p
                            (recur (next p) (cons (first p) d))
                            d))]
               (cons 'clojure.core/defn decl))))

(defmacro when
  "Evaluates test. If logical true, evaluates body in an implicit do."
  {:added "1.0"}
  [test & body]
  (list 'if test (cons 'do body)))

(defmacro when-not
  "Evaluates test. If logical false, evaluates body in an implicit do."
  {:added "1.0"}
  [test & body]
  (list 'if test nil (cons 'do body)))

(defn false?
  "Returns true if x is the value false, false otherwise."
  {:tag Boolean,
   :added "1.0"
   :static true}
  [x] (erlang/=:=.e x false))

(defn true?
  "Returns true if x is the value true, false otherwise."
  {:tag Boolean,
   :added "1.0"
   :static true}
  [x] (erlang/=:=.e x true))

(defn not
  "Returns true if x is logical false, false otherwise."
  {:tag Boolean
   :added "1.0"
   :static true}
  [x] (if x false true))

(defn some?
  "Returns true if x is not nil, false otherwise."
  {:tag Boolean
   :added "1.6"
   :static true}
  [x] (not (nil? x)))

(defn str
  "With no args, returns the empty string. With one arg x, returns
  x.toString().  (str nil) returns the empty string. With more than
  one arg, returns the concatenation of the str values of the args."
  {:tag :clojerl.String
   :added "1.0"
   :static true}
  (^:clojerl.String [] "")
  (^:clojerl.String [^Object x]
                    (if (nil? x) "" (clj_core/str.e x)))
  (^:clojerl.String [x & ys]
                    ((fn [acc more]
                       (if more
                         (recur (clj_utils/binary_append.e acc (str (first more)))
                                (next more))
                         acc))
                     (clj_core/str.e x) ys)))

(defn keyword?
  "Return true if x is a Keyword"
  {:added "1.0"
   :static true}
  [x] (instance? :clojerl.Keyword x))

(defn symbol
  "Returns a Symbol with the given namespace and name."
  {:tag clojure.lang.Symbol
   :added "1.0"
   :static true}
  ([name] (if (symbol? name) name (clj_core/symbol.e name)))
  ([ns name] (clj_core/symbol.e ns name)))

(defn gensym
  "Returns a new symbol with a unique name. If a prefix string is
  supplied, the name is prefix# where # is some unique number. If
  prefix is not supplied, the prefix is 'G__'."
  {:added "1.0"
   :static true}
  ([] (gensym "G__"))
  ([prefix-string] (clj_core/gensym.e prefix-string)))

(defmacro cond
  "Takes a set of test/expr pairs. It evaluates each test one at a
  time.  If a test returns logical true, cond evaluates and returns
  the value of the corresponding expr and doesn't evaluate any of the
  other tests or exprs. (cond) returns nil."
  {:added "1.0"}
  [& clauses]
  (when clauses
    (list 'if (first clauses)
          (if (next clauses)
            (second clauses)
            (throw "cond requires an even number of forms"))
          (cons 'clojure.core/cond (nnext clauses)))))

(defn keyword
  "Returns a Keyword with the given namespace and name.  Do not use :
  in the keyword strings, it will be added automatically."
  {:tag clojure.lang.Keyword
   :added "1.0"
   :static true}
  ([name] (cond (keyword? name) name
                (symbol? name) (clojerl.Keyword/new.e (clj_core/namespace.e name)
                                                      (clj_core/name.e name))
                (string? name) (clojerl.Keyword/new.e name)))
  ([ns name] (clojerl.Keyword/new.e ns name)))

(defn find-keyword
  "Returns a Keyword with the given namespace and name if one already
  exists.  This function will not intern a new keyword. If the keyword
  has not already been interned, it will return nil.  Do not use :
  in the keyword strings, it will be added automatically."
  {:tag clojure.lang.Keyword
   :added "1.3"
   :static true}
  ([name] (cond (keyword? name) name
                (symbol? name) (clojerl.Keyword/find.e (clj_core/namespace.e name)
                                                       (clj_core/name.e name))
                (string? name) (clojerl.Keyword/find.e name)))
  ([ns name] (clojerl.Keyword/find.e ns name)))

(defn spread
  {:private true
   :static true}
  [arglist]
  (cond
    (nil? arglist) nil
    (nil? (next arglist)) (seq (first arglist))
    :else (cons (first arglist) (spread (next arglist)))))

(defn list*
  "Creates a new list containing the items prepended to the rest, the
  last of which will be treated as a sequence."
  {:added "1.0"
   :static true}
  ([args] (seq args))
  ([a args] (cons a args))
  ([a b args] (cons a (cons b args)))
  ([a b c args] (cons a (cons b (cons c args))))
  ([a b c d & more]
   (cons a (cons b (cons c (cons d (spread more)))))))

(defn apply
  "Applies fn f to the argument list formed by prepending intervening arguments to args."
  {:added "1.0"
   :static true}
  ([f args]
   (clj_core/invoke.e f (seq args)))
  ([f x args]
   (clj_core/invoke.e f (list* x args)))
  ([f x y args]
   (clj_core/invoke.e f (list* x y args)))
  ([f x y z args]
   (clj_core/invoke.e f (list* x y z args)))
  ([f a b c d args]
   (clj_core/invoke.e f (cons a (cons b (cons c (cons d (spread args))))))))

(defn vary-meta
  "Returns an object of the same type and value as obj, with
  (apply f (meta obj) args) as its metadata."
  {:added "1.0"
   :static true}
  [obj f & args]
  (with-meta obj (apply f (meta obj) args)))

(defmacro lazy-seq
  "Takes a body of expressions that returns an ISeq or nil, and yields
  a Seqable object that will invoke the body only the first time seq
  is called, and will cache the result and return it on all subsequent
  seq calls. See also - realized?"
  {:added "1.0"}
  [& body]
  (list 'clojerl.LazySeq/new.e (list* '^{:once true} fn* [] body)))

(defn ^:static ^clojure.lang.ChunkBuffer chunk-buffer ^clojure.lang.ChunkBuffer [capacity]
  (throw "unimplemented")
  #_(clojure.lang.ChunkBuffer. capacity))

(defn ^:static chunk-append [^clojure.lang.ChunkBuffer b x]
  (throw "unimplemented")
  #_(.add b x))

(defn ^:static ^clojure.lang.IChunk chunk [^clojure.lang.ChunkBuffer b]
  (throw "unimplemented")
  #_(.chunk b))

(defn ^:static  ^clojure.lang.IChunk chunk-first ^clojure.lang.IChunk [^clojure.lang.IChunkedSeq s]
  (throw "unimplemented")
  #_(.chunkedFirst s))

(defn ^:static ^clojure.lang.ISeq chunk-rest ^clojure.lang.ISeq [^clojure.lang.IChunkedSeq s]
  (throw "unimplemented")
  #_(.chunkedMore s))

(defn ^:static ^clojure.lang.ISeq chunk-next ^clojure.lang.ISeq [^clojure.lang.IChunkedSeq s]
  (throw "unimplemented")
  #_(.chunkedNext s))

(defn ^:static chunk-cons [chunk rest]
  (throw "unimplemented")
  #_(if (clojure.lang.Numbers/isZero (clojure.lang.RT/count chunk))
      rest
      (clojure.lang.ChunkedCons. chunk rest)))

(defn ^:static chunked-seq? [s]
  (throw "unimplemented")
  #_(instance? clojure.lang.IChunkedSeq s))

#_(defn concat
    "Returns a lazy seq representing the concatenation of the elements in the supplied colls."
    {:added "1.0"
     :static true}
    ([] (lazy-seq nil))
    ([x] (lazy-seq x))
    ([x y]
     (lazy-seq
      (let [s (seq x)]
        (if s
          (cons (first s) (concat (rest s) y))
          y))))
    ([x y & zs]
     (let [cat (fn cat [xys zs]
                 (lazy-seq
                  (let [xys (seq xys)]
                    (if xys
                      (cons (first xys) (cat (rest xys) zs))
                      (when zs
                        (cat (first zs) (next zs)))))))]
       (cat (concat x y) zs))))

(def concat
  (fn*
   ([] (list))
   ([x] (apply list x))
   ([x y]
    (if (seq x)
      (cons (first (seq x)) (concat (rest (seq x)) y))
      y))
   ([x y & zs]
    (if (seq zs)
      (apply concat (concat x y) (first zs) (next zs))
      (concat x y)))))

;;;;;;;;;;;;;;;;at this point all the support for syntax-quote exists;;;;;;;;;;;;;;;;;;;;;;
(defmacro delay
  "Takes a body of expressions and yields a Delay object that will
  invoke the body only the first time it is forced (with force or deref/@), and
  will cache the result and return it on all subsequent force
  calls. See also - realized?"
  {:added "1.0"}
  [& body]
    (list 'clojerl.Delay/new.e (list* `^{:once true} fn* [] body)))

(defn delay?
  "returns true if x is a Delay created with delay"
  {:added "1.0"
   :static true}
  [x]
  (instance? :clojerl.Delay x))

(defn force
  "If x is a Delay, returns the (possibly cached) value of its expression, else returns x"
  {:added "1.0"
   :static true}
  [x]
  (clojerl.Delay/force.e x))

(defmacro if-not
  "Evaluates test. If logical false, evaluates and returns then expr,
  otherwise else expr, if supplied, else nil."
  {:added "1.0"}
  ([test then] `(if-not ~test ~then nil))
  ([test then else]
   `(if (not ~test) ~then ~else)))

(defn identical?
  "Tests if 2 arguments are the same object"
  {:inline (fn [x y] `(erlang/=:=.e ~x ~y))
   :inline-arities #{2}
   :added "1.0"}
  ([x y] (erlang/=:=.e x y)))

;equiv-based
(defn =
  "Equality. Returns true if x equals y, false if not. Same as
  Java x.equals(y) except it also works for nil, and compares
  numbers and collections in a type-independent manner.  Clojure's immutable data
  structures define equals() (and thus =) as a value, not an identity,
  comparison."
  {:inline (fn [x y] `(clj_core/equiv.e ~x ~y))
   :inline-arities #{2}
   :added "1.0"}
  ([x] true)
  ([x y] (clj_core/equiv.e x y))
  ([x y & more]
   (if (clj_core/equiv.e x y)
     (if (next more)
       (recur y (first more) (next more))
       (clj_core/equiv.e y (first more)))
     false)))

;equals-based
#_(defn =
  "Equality. Returns true if x equals y, false if not. Same as Java
  x.equals(y) except it also works for nil. Boxed numbers must have
  same type. Clojure's immutable data structures define equals() (and
  thus =) as a value, not an identity, comparison."
  {:inline (fn [x y] `(. clojure.lang.Util equals ~x ~y))
   :inline-arities #{2}
   :added "1.0"}
  ([x] true)
  ([x y] (clojure.lang.Util/equals x y))
  ([x y & more]
   (if (= x y)
     (if (next more)
       (recur y (first more) (next more))
       (= y (first more)))
     false)))

(defn not=
  "Same as (not (= obj1 obj2))"
  {:tag Boolean
   :added "1.0"
   :static true}
  ([x] false)
  ([x y] (not (= x y)))
  ([x y & more]
   (not (apply = x y more))))

(defn compare
  "Comparator. Returns a negative number, zero, or a positive number
  when x is logically 'less than', 'equal to', or 'greater than'
  y. Same as Java x.compareTo(y) except it also works for nil, and
  compares numbers and collections in a type-independent manner. x
  must implement Comparable"
  {
   :inline (fn [x y] `(. clojure.lang.Util compare ~x ~y))
   :added "1.0"}
  [x y]
  (throw "unimplemented")
  #_(. clojure.lang.Util (compare x y)))

(defmacro and
  "Evaluates exprs one at a time, from left to right. If a form
  returns logical false (nil or false), and returns that value and
  doesn't evaluate any of the other expressions, otherwise it returns
  the value of the last expr. (and) returns true."
  {:added "1.0"}
  ([] true)
  ([x] x)
  ([x & next]
   `(let [and# ~x]
      (if and# (and ~@next) and#))))

(defmacro or
  "Evaluates exprs one at a time, from left to right. If a form
  returns a logical true value, or returns that value and doesn't
  evaluate any of the other expressions, otherwise it returns the
  value of the last expression. (or) returns nil."
  {:added "1.0"}
  ([] nil)
  ([x] x)
  ([x & next]
      `(let [or# ~x]
         (if or# or# (or ~@next)))))

;;;;;;;;;;;;;;;;;;; sequence fns  ;;;;;;;;;;;;;;;;;;;;;;;
(defn zero?
  "Returns true if num is zero, else false"
  {:inline (fn [x] `(erlang/==.e ~x 0))
   :added "1.0"}
  [x] (erlang/==.e x 0))

(defn count
  "Returns the number of items in the collection. (count nil) returns
  0.  Also works on strings, arrays, and Java Collections and Maps"
  {:inline (fn  [x] `(clj_core/count.e ~x))
   :added "1.0"}
  [coll] (clj_core/count.e coll))

(defn int
  "Coerce to int"
  {
   :inline (fn  [x] `(clj_core/to_int.e ~x))
   :added "1.0"}
  [x] (clj_core/to_int.e x))

(defn nth
  "Returns the value at the index. get returns nil if index out of
  bounds, nth throws an exception unless not-found is supplied.  nth
  also works for strings, Java arrays, regex Matchers and Lists, and,
  in O(n) time, for sequences."
  {:inline (fn  [c i & nf] `(clj_core/nth.e ~c ~i ~@nf))
   :inline-arities #{2 3}
   :added "1.0"}
  ([coll index] (clj_core/nth.e coll index))
  ([coll index not-found] (clj_core/nth.e coll index not-found)))

(defn <
  "Returns non-nil if nums are in monotonically increasing order,
  otherwise false."
  {:inline (fn [x y] `(erlang/<.e ~x ~y))
   :inline-arities #{2}
   :added "1.0"}
  ([x] true)
  ([x y] (erlang/<.e x y))
  ([x y & more]
   (if (< x y)
     (if (next more)
       (recur y (first more) (next more))
       (< y (first more)))
     false)))

(defn inc'
  "Returns a number one greater than num. Supports arbitrary precision.
  See also: inc"
  {:inline (fn [x] `(erlang/+.e ~x 1))
   :added "1.0"}
  [x] (erlang/+.e x 1))

(defn inc
  "Returns a number one greater than num. Does not auto-promote
  longs, will throw on overflow. See also: inc'"
  {:inline (fn [x] `(inc' ~x))
   :added "1.2"}
  [x] (inc' x))

;; reduce is defined again later after InternalReduce loads
(defn ^:private ^:static
  reduce1
  ([f coll]
   (let [s (seq coll)]
     (if s
       (reduce1 f (first s) (next s))
       (f))))
  ([f val coll]
   (let [s (seq coll)]
     (if s
       (recur f (f val (first s)) (next s))
       val))))

(defn reverse
  "Returns a seq of the items in coll in reverse order. Not lazy."
  {:added "1.0"
   :static true}
  [coll]
  (reduce1 conj '() coll))

;;math stuff
(defn ^:private nary-inline
  ([op] (nary-inline op op))
  ([op unchecked-op]
   (throw "unsupported")
   (fn
     ([x] #_`(. clojure.lang.Numbers (~op ~x)))
     ([x y] #_`(. clojure.lang.Numbers (~op ~x ~y)))
     ([x y & more]
      #_(reduce1
         (fn [a b] `(. clojure.lang.Numbers (~op ~a ~b)))
         `(. clojure.lang.Numbers (~op ~x ~y)) more)))))

(defn ^:private >1? [n] (erlang/>.e n 1))
(defn ^:private >0? [n] (erlang/>.e n 0))

(defn +'
  "Returns the sum of nums. (+) returns 0. Supports arbitrary precision.
  See also: +"
  {:inline (nary-inline 'addP)
   :inline-arities >1?
   :added "1.0"}
  ([] 0)
  ([x] x)
  ([x y] (erlang/+.e x y))
  ([x y & more]
   (reduce1 +' (+' x y) more)))

(defn +
  "Returns the sum of nums. (+) returns 0. Does not auto-promote
  longs, will throw on overflow. See also: +'"
  {:inline (nary-inline 'add 'unchecked_add)
   :inline-arities >1?
   :added "1.2"}
  ([] 0)
  ([x] x)
  ([x y] (+' x y))
  ([x y & more]
   (reduce1 + (+ x y) more)))

(defn *'
  "Returns the product of nums. (*) returns 1. Supports arbitrary precision.
  See also: *"
  {:inline (nary-inline 'multiplyP)
   :inline-arities >1?
   :added "1.0"}
  ([] 1)
  ([x] x)
  ([x y] (erlang/*.e x y))
  ([x y & more]
   (reduce1 *' (*' x y) more)))

(defn *
  "Returns the product of nums. (*) returns 1. Does not auto-promote
  longs, will throw on overflow. See also: *'"
  {:inline (nary-inline 'multiply 'unchecked_multiply)
   :inline-arities >1?
   :added "1.2"}
  ([] 1)
  ([x] x)
  ([x y] (*' x y))
  ([x y & more]
   (reduce1 * (* x y) more)))

(defn /
  "If no denominators are supplied, returns 1/numerator,
  else returns numerator divided by all of the denominators."
  {:inline (nary-inline 'divide)
   :inline-arities >1?
   :added "1.0"}
  ([x] (/ 1 x))
  ([x y] (erlang//.e x y))
  ([x y & more]
   (reduce1 / (/ x y) more)))

(defn -'
  "If no ys are supplied, returns the negation of x, else subtracts
  the ys from x and returns the result. Supports arbitrary precision.
  See also: -"
  {:inline (nary-inline 'minusP)
   :inline-arities >0?
   :added "1.0"}
  ([x] (erlang/-.e x))
  ([x y] (erlang/-.e x y))
  ([x y & more]
   (reduce1 -' (-' x y) more)))

(defn -
  "If no ys are supplied, returns the negation of x, else subtracts
  the ys from x and returns the result. Does not auto-promote
  longs, will throw on overflow. See also: -'"
  {:inline (nary-inline 'minus 'unchecked_minus)
   :inline-arities >0?
   :added "1.2"}
  ([x] (-' x))
  ([x y] (-' x y))
  ([x y & more]
   (reduce1 - (- x y) more)))

(defn <=
  "Returns non-nil if nums are in monotonically non-decreasing order,
  otherwise false."
  {:inline (fn [x y] `(erlang/=<.e ~x ~y))
   :inline-arities #{2}
   :added "1.0"}
  ([x] true)
  ([x y] (erlang/=<.e x y))
  ([x y & more]
   (if (<= x y)
     (if (next more)
       (recur y (first more) (next more))
       (<= y (first more)))
     false)))

(defn >
  "Returns non-nil if nums are in monotonically decreasing order,
  otherwise false."
  {:inline (fn [x y] `(erlang/>.e ~x ~y))
   :inline-arities #{2}
   :added "1.0"}
  ([x] true)
  ([x y] (erlang/>.e x y))
  ([x y & more]
   (if (> x y)
     (if (next more)
       (recur y (first more) (next more))
       (> y (first more)))
     false)))

(defn >=
  "Returns non-nil if nums are in monotonically non-increasing order,
  otherwise false."
  {:inline (fn [x y] `(erlang/>=.e ~x ~y))
   :inline-arities #{2}
   :added "1.0"}
  ([x] true)
  ([x y] (erlang/>=.e x y))
  ([x y & more]
   (if (>= x y)
     (if (next more)
       (recur y (first more) (next more))
       (>= y (first more)))
     false)))

(defn ==
  "Returns non-nil if nums all have the equivalent
  value (type-independent), otherwise false"
  {:inline (fn [x y] `(erlang/==.e ~x ~y))
   :inline-arities #{2}
   :added "1.0"}
  ([x] true)
  ([x y] (erlang/==.e x y))
  ([x y & more]
   (if (== x y)
     (if (next more)
       (recur y (first more) (next more))
       (== y (first more)))
     false)))

(defn max
  "Returns the greatest of the nums."
  {:added "1.0"
   :inline-arities >1?
   :inline (nary-inline 'max)}
  ([x] x)
  ([x y] (if (< x y) y x))
  ([x y & more]
   (reduce1 max (max x y) more)))

(defn min
  "Returns the least of the nums."
  {:added "1.0"
   :inline-arities >1?
   :inline (nary-inline 'min)}
  ([x] x)
  ([x y] (if (> x y) y x))
  ([x y & more]
   (reduce1 min (min x y) more)))

(defn dec'
  "Returns a number one less than num. Supports arbitrary precision.
  See also: dec"
  {:inline (fn [x] `(erlang/-.e ~x 1))
   :added "1.0"}
  [x] (erlang/-.e x 1))

(defn dec
  "Returns a number one less than num. Does not auto-promote
  longs, will throw on overflow. See also: dec'"
  {:inline (fn [x] `(dec' ~x))
   :added "1.2"}
  [x] (dec' x))

(defn unchecked-inc-int
  "Returns a number one greater than x, an int.
  Note - uses a primitive operator subject to overflow."
  {:inline (fn [x] `(. clojure.lang.Numbers (unchecked_int_inc ~x)))
   :added "1.0"}
  [x]
  (throw "unsupported")
  #_(. clojure.lang.Numbers (unchecked_int_inc x)))

(defn unchecked-inc
  "Returns a number one greater than x, a long.
  Note - uses a primitive operator subject to overflow."
  {:inline (fn [x] `(. clojure.lang.Numbers (unchecked_inc ~x)))
   :added "1.0"}
  [x]
  (throw "unsupported")
  #_(. clojure.lang.Numbers (unchecked_inc x)))

(defn unchecked-dec-int
  "Returns a number one less than x, an int.
  Note - uses a primitive operator subject to overflow."
  {:inline (fn [x] `(. clojure.lang.Numbers (unchecked_int_dec ~x)))
   :added "1.0"}
  [x]
  (throw "unsupported")
  #_(. clojure.lang.Numbers (unchecked_int_dec x)))

(defn unchecked-dec
  "Returns a number one less than x, a long.
  Note - uses a primitive operator subject to overflow."
  {:inline (fn [x] `(. clojure.lang.Numbers (unchecked_dec ~x)))
   :added "1.0"}
  [x]
  (throw "unsupported")
  #_(. clojure.lang.Numbers (unchecked_dec x)))

(defn unchecked-negate-int
  "Returns the negation of x, an int.
  Note - uses a primitive operator subject to overflow."
  {:inline (fn [x] `(. clojure.lang.Numbers (unchecked_int_negate ~x)))
   :added "1.0"}
  [x]
  (throw "unsupported")
  #_(. clojure.lang.Numbers (unchecked_int_negate x)))

(defn unchecked-negate
  "Returns the negation of x, a long.
  Note - uses a primitive operator subject to overflow."
  {:inline (fn [x] `(. clojure.lang.Numbers (unchecked_minus ~x)))
   :added "1.0"}
  [x]
  (throw "unsupported")
  #_(. clojure.lang.Numbers (unchecked_minus x)))

(defn unchecked-add-int
  "Returns the sum of x and y, both int.
  Note - uses a primitive operator subject to overflow."
  {:inline (fn [x y] `(. clojure.lang.Numbers (unchecked_int_add ~x ~y)))
   :added "1.0"}
  [x y]
  (throw "unsupported")
  #_(. clojure.lang.Numbers (unchecked_int_add x y)))

(defn unchecked-add
  "Returns the sum of x and y, both long.
  Note - uses a primitive operator subject to overflow."
  {:inline (fn [x y] `(. clojure.lang.Numbers (unchecked_add ~x ~y)))
   :added "1.0"}
  [x y]
  (throw "unsupported")
  #_(. clojure.lang.Numbers (unchecked_add x y)))

(defn unchecked-subtract-int
  "Returns the difference of x and y, both int.
  Note - uses a primitive operator subject to overflow."
  {:inline (fn [x y] `(. clojure.lang.Numbers (unchecked_int_subtract ~x ~y)))
   :added "1.0"}
  [x y]
  (throw "unsupported")
  #_(. clojure.lang.Numbers (unchecked_int_subtract x y)))

(defn unchecked-subtract
  "Returns the difference of x and y, both long.
  Note - uses a primitive operator subject to overflow."
  {:inline (fn [x y] `(. clojure.lang.Numbers (unchecked_minus ~x ~y)))
   :added "1.0"}
  [x y]
  (throw "unsupported")
  #_(. clojure.lang.Numbers (unchecked_minus x y)))

(defn unchecked-multiply-int
  "Returns the product of x and y, both int.
  Note - uses a primitive operator subject to overflow."
  {:inline (fn [x y] `(. clojure.lang.Numbers (unchecked_int_multiply ~x ~y)))
   :added "1.0"}
  [x y]
  (throw "unsupported")
  #_(. clojure.lang.Numbers (unchecked_int_multiply x y)))

(defn unchecked-multiply
  "Returns the product of x and y, both long.
  Note - uses a primitive operator subject to overflow."
  {:inline (fn [x y] `(. clojure.lang.Numbers (unchecked_multiply ~x ~y)))
   :added "1.0"}
  [x y]
  (throw "unsupported")
  #_(. clojure.lang.Numbers (unchecked_multiply x y)))

(defn unchecked-divide-int
  "Returns the division of x by y, both int.
  Note - uses a primitive operator subject to truncation."
  {:inline (fn [x y] `(. clojure.lang.Numbers (unchecked_int_divide ~x ~y)))
   :added "1.0"}
  [x y]
  (throw "unsupported")
  #_(. clojure.lang.Numbers (unchecked_int_divide x y)))

(defn unchecked-remainder-int
  "Returns the remainder of division of x by y, both int.
  Note - uses a primitive operator subject to truncation."
  {:inline (fn [x y] `(. clojure.lang.Numbers (unchecked_int_remainder ~x ~y)))
   :added "1.0"}
  [x y]
  (throw "unsupported")
  #_(. clojure.lang.Numbers (unchecked_int_remainder x y)))

(defn pos?
  "Returns true if num is greater than zero, else false"
  {:inline (fn [x] `(> ~x 0))
   :added "1.0"}
  [x] (> x 0))

(defn neg?
  "Returns true if num is less than zero, else false"
  {:inline (fn [x] `(< ~x 0))
   :added "1.0"}
  [x] (< x 0))

(defn quot
  "quot[ient] of dividing numerator by denominator."
  {:added "1.0"
   :static true
   :inline (fn [x y] `(erlang/trunc.e (/ ~x ~y)))}
  [num div]
  (erlang/trunc.e (/ num div)))

(defn rem
  "remainder of dividing numerator by denominator."
  {:added "1.0"
   :static true
   :inline (fn [x y] `(erlang/rem.e ~x ~y))}
  [num div]
  (erlang/rem.e num div))

(defn rationalize
  "returns the rational value of num"
  {:added "1.0"
   :static true}
  [num]
  (throw "unimplemented")
  #_(. clojure.lang.Numbers (rationalize num)))

;;Bit ops

(defn bit-not
  "Bitwise complement"
  {:inline (fn [x] `(erlang/bnot.e ~x))
   :added "1.0"}
  [x] (erlang/bnot.e x))


(defn bit-and
  "Bitwise and"
   {:inline (nary-inline 'and)
    :inline-arities >1?
    :added "1.0"}
   ([x y] (erlang/band.e x y))
   ([x y & more]
      (reduce1 bit-and (bit-and x y) more)))

(defn bit-or
  "Bitwise or"
  {:inline (nary-inline 'or)
   :inline-arities >1?
   :added "1.0"}
  ([x y] (erlang/bor.e x y))
  ([x y & more]
    (reduce1 bit-or (bit-or x y) more)))

(defn bit-xor
  "Bitwise exclusive or"
  {:inline (nary-inline 'xor)
   :inline-arities >1?
   :added "1.0"}
  ([x y] (erlang/bxor.e x y))
  ([x y & more]
    (reduce1 bit-xor (bit-xor x y) more)))

(defn bit-and-not
  "Bitwise and with complement"
  {:inline (nary-inline 'andNot)
   :inline-arities >1?
   :added "1.0"
   :static true}
  ([x y] (bit-not (bit-and x y)))
  ([x y & more]
    (reduce1 bit-and-not (bit-and-not x y) more)))


(defn bit-clear
  "Clear bit at index n"
  {:added "1.0"
   :static true}
  [x n]
  (throw "unimplemented")
  #_(. clojure.lang.Numbers clearBit x n))

(defn bit-set
  "Set bit at index n"
  {:added "1.0"
   :static true}
  [x n]
  (throw "unimplemented")
  #_(. clojure.lang.Numbers setBit x n))

(defn bit-flip
  "Flip bit at index n"
  {:added "1.0"
   :static true}
  [x n]
  (throw "unimplemented")
  #_(. clojure.lang.Numbers flipBit x n))

(defn bit-test
  "Test bit at index n"
  {:added "1.0"
   :static true}
  [x n]
  (throw "unimplemented")
  #_(. clojure.lang.Numbers testBit x n))


(defn bit-shift-left
  "Bitwise shift left"
  {:inline (fn [x n] `(erlang/bsl.e ~x ~n))
   :added "1.0"}
  [x n] (erlang/bsl.e x n))

(defn bit-shift-right
  "Bitwise shift right"
  {:inline (fn [x n] `(erlang/bsr.e ~x ~n))
   :added "1.0"}
  [x n] (erlang/bsr.e x n))

(defn unsigned-bit-shift-right
  "Bitwise shift right, without sign-extension."
  {:inline (fn [x n] `(erlang/bsr.e ~x ~n))
   :added "1.6"}
  [x n]
  (throw "unimplemented")
  #_(. clojure.lang.Numbers unsignedShiftRight x n))

(defn integer?
  "Returns true if n is an integer"
  {:added "1.0"
   :static true}
  [n]
  (erlang/is_integer.e n))

(defn even?
  "Returns true if n is even, throws an exception if n is not an integer"
  {:added "1.0"
   :static true}
   [n] (if (integer? n)
        (zero? (bit-and n 1))
        (throw (str "Argument must be an integer: " n))))

(defn odd?
  "Returns true if n is odd, throws an exception if n is not an integer"
  {:added "1.0"
   :static true}
  [n] (not (even? n)))


;;

(defn complement
  "Takes a fn f and returns a fn that takes the same arguments as f,
  has the same effects, if any, and returns the opposite truth value."
  {:added "1.0"
   :static true}
  [f]
  (fn
    ([] (not (f)))
    ([x] (not (f x)))
    ([x y] (not (f x y)))
    ([x y & zs] (not (apply f x y zs)))))

(defn constantly
  "Returns a function that takes any number of arguments and returns x."
  {:added "1.0"
   :static true}
  [x] (fn [& args] x))

(defn identity
  "Returns its argument."
  {:added "1.0"
   :static true}
  [x] x)

;;Collection stuff

;;list stuff
(defn peek
  "For a list or queue, same as first, for a vector, same as, but much
  more efficient than, last. If the collection is empty, returns nil."
  {:added "1.0"
   :static true}
  [coll] (erlang/peek.e coll))

(defn pop
  "For a list or queue, returns a new list/queue without the first
  item, for a vector, returns a new vector without the last item. If
  the collection is empty, throws an exception.  Note - not the same
  as next/butlast."
  {:added "1.0"
   :static true}
  [coll] (erlang/pop.e coll))

;;map stuff

(defn contains?
  "Returns true if key is present in the given collection, otherwise
  returns false.  Note that for numerically indexed collections like
  vectors and Java arrays, this tests if the numeric key is within the
  range of indexes. 'contains?' operates constant or logarithmic time;
  it will not perform a linear search for a value.  See also 'some'."
  {:added "1.0"
   :static true}
  [coll key] (clj_core/contains?.e coll key))

(defn get
  "Returns the value mapped to key, not-found or nil if key not present."
  {:inline (fn  [m k & nf] `(clj_core/get.e ~m ~k ~@nf))
   :inline-arities #{2 3}
   :added "1.0"}
  ([map key]
   (clj_core/get.e map key))
  ([map key not-found]
   (clj_core/get.e map key not-found)))

(defn dissoc
  "dissoc[iate]. Returns a new map of the same (hashed/sorted) type,
  that does not contain a mapping for key(s)."
  {:added "1.0"
   :static true}
  ([map] map)
  ([map key]
   (clj_core/dissoc.e map key))
  ([map key & ks]
   (let [ret (dissoc map key)]
     (if ks
       (recur ret (first ks) (next ks))
       ret))))

(defn disj
  "disj[oin]. Returns a new set of the same (hashed/sorted) type, that
  does not contain key(s)."
  {:added "1.0"
   :static true}
  ([set] set)
  ([^clojure.lang.IPersistentSet set key]
   (when set
     (clj_core/disj.e set key)))
  ([set key & ks]
   (when set
     (let [ret (disj set key)]
       (if ks
         (recur ret (first ks) (next ks))
         ret)))))

(defn find
  "Returns the map entry for key, or nil if key not present."
  {:added "1.0"
   :static true}
  [map key] (clj_core/find.e map key))

(defn select-keys
  "Returns a map containing only those entries in map whose key is in keys"
  {:added "1.0"
   :static true}
  [map keyseq]
    (loop [ret {} keys (seq keyseq)]
      (if keys
        (let [entry (clj_core/find.e map (first keys))]
          (recur
           (if entry
             (conj ret entry)
             ret)
           (next keys)))
        (with-meta ret (meta map)))))

(defn keys
  "Returns a sequence of the map's keys, in the same order as (seq map)."
  {:added "1.0"
   :static true}
  [map] (clj_core/keys.e map))

(defn vals
  "Returns a sequence of the map's values, in the same order as (seq map)."
  {:added "1.0"
   :static true}
  [map] (clj_core/vals.e map))

(defn key
  "Returns the key of the map entry."
  {:added "1.0"
   :static true}
  [e]
  (first e))

(defn val
  "Returns the value in the map entry."
  {:added "1.0"
   :static true}
  [e]
  (second e))

(defn rseq
  "Returns, in constant time, a seq of the items in rev (which
  can be a vector or sorted-map), in reverse order. If rev is empty returns nil"
  {:added "1.0"
   :static true}
  [rev]
    (clj_core/rseq.e rev))

(defn name
  "Returns the name String of a string, symbol or keyword."
  {:tag String
   :added "1.0"
   :static true}
  [x]
  (if (string? x) x (clj_core/name.e x)))

(defn namespace
  "Returns the namespace String of a symbol or keyword, or nil if not present."
  {:tag String
   :added "1.0"
   :static true}
  [^clojure.lang.Named x]
  (clj_core/namespace.e x))

(defmacro locking
  "Executes exprs in an implicit do, while holding the monitor of x.
  Will release the monitor of x in all circumstances."
  {:added "1.0"}
  [x & body]
  (throw "unsupported")
  #_`(let [lockee# ~x]
     (try
      (monitor-enter lockee#)
      ~@body
      (finally
       (monitor-exit lockee#)))))

(defmacro ..
  "form => fieldName-symbol or (instanceMethodName-symbol args*)
  Expands into a member access (.) of the first member on the first
  argument, followed by the next member on the result, etc. For
  instance:
  (.. System (getProperties) (get \"os.name\"))
  expands to:
  (. (. System (getProperties)) (get \"os.name\"))
  but is easier to write, read, and understand."
  {:added "1.0"}
  ([x form] `(. ~x ~form))
  ([x form & more] `(.. (. ~x ~form) ~@more)))

(defmacro ->
  "Threads the expr through the forms. Inserts x as the
  second item in the first form, making a list of it if it is not a
  list already. If there are more forms, inserts the first form as the
  second item in second form, etc."
  {:added "1.0"}
  [x & forms]
  (loop [x x, forms forms]
    (if forms
      (let [form (first forms)
            threaded (if (seq? form)
                       (with-meta `(~(first form) ~x ~@(next form)) (meta form))
                       (list form x))]
        (recur threaded (next forms)))
      x)))

(defmacro ->>
  "Threads the expr through the forms. Inserts x as the
  last item in the first form, making a list of it if it is not a
  list already. If there are more forms, inserts the first form as the
  last item in second form, etc."
  {:added "1.1"}
  [x & forms]
  (loop [x x, forms forms]
    (if forms
      (let [form (first forms)
            threaded (if (seq? form)
              (with-meta `(~(first form) ~@(next form)  ~x) (meta form))
              (list form x))]
        (recur threaded (next forms)))
      x)))

(def map)

(defn ^:private check-valid-options
  "Throws an exception if the given option map contains keys not listed
  as valid, else returns nil."
  [options & valid-keys]
  (when (seq (apply disj (apply hash-set (keys options)) valid-keys))
    (throw
      (apply str "Only these options are valid: "
             (first valid-keys)
             (map #(str ", " %) (rest valid-keys))))))

;;multimethods
;; MISSING

;;;;;;;;; var stuff

(defmacro ^{:private true} assert-args
  [& pairs]
  `(do (when-not ~(first pairs)
         (throw (str (first ~'&form) " requires " ~(second pairs) " in " ~'*ns* ":" (:line (meta ~'&form)))))
     ~(let [more (nnext pairs)]
        (when more
          (list* `assert-args more)))))

(defmacro if-let
  "bindings => binding-form test
  If test is true, evaluates then with binding-form bound to the value of
  test, if not, yields else"
  {:added "1.0"}
  ([bindings then]
   `(if-let ~bindings ~then nil))
  ([bindings then else & oldform]
   (assert-args
     (vector? bindings) "a vector for its binding"
     (nil? oldform) "1 or 2 forms after binding vector"
     (= 2 (count bindings)) "exactly 2 forms in binding vector")
   (let [form (bindings 0) tst (bindings 1)]
     `(let [temp# ~tst]
        (if temp#
          (let [~form temp#]
            ~then)
          ~else)))))

(defmacro when-let
  "bindings => binding-form test
  When test is true, evaluates body with binding-form bound to the value of test"
  {:added "1.0"}
  [bindings & body]
  (assert-args
     (vector? bindings) "a vector for its binding"
     (= 2 (count bindings)) "exactly 2 forms in binding vector")
   (let [form (bindings 0) tst (bindings 1)]
    `(let [temp# ~tst]
       (when temp#
         (let [~form temp#]
           ~@body)))))

(defmacro if-some
  "bindings => binding-form test
   If test is not nil, evaluates then with binding-form bound to the
   value of test, if not, yields else"
  {:added "1.6"}
  ([bindings then]
   `(if-some ~bindings ~then nil))
  ([bindings then else & oldform]
   (assert-args
     (vector? bindings) "a vector for its binding"
     (nil? oldform) "1 or 2 forms after binding vector"
     (= 2 (count bindings)) "exactly 2 forms in binding vector")
   (let [form (bindings 0) tst (bindings 1)]
     `(let [temp# ~tst]
        (if (nil? temp#)
          ~else
          (let [~form temp#]
            ~then))))))

(defmacro when-some
  "bindings => binding-form test
   When test is not nil, evaluates body with binding-form bound to the
   value of test"
  {:added "1.6"}
  [bindings & body]
  (assert-args
     (vector? bindings) "a vector for its binding"
     (= 2 (count bindings)) "exactly 2 forms in binding vector")
   (let [form (bindings 0) tst (bindings 1)]
    `(let [temp# ~tst]
       (if (nil? temp#)
         nil
         (let [~form temp#]
           ~@body)))))

;; MISSING

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;; Refs ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; agent, atom, ref
;; MISSING

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;; fn stuff ;;;;;;;;;;;;;;;;

(defn comp
  "Takes a set of functions and returns a fn that is the composition
  of those fns.  The returned fn takes a variable number of args,
  applies the rightmost of fns to the args, the next
  fn (right-to-left) to the result, etc."
  {:added "1.0"
   :static true}
  ([] identity)
  ([f] f)
  ([f g]
     (fn
       ([] (f (g)))
       ([x] (f (g x)))
       ([x y] (f (g x y)))
       ([x y z] (f (g x y z)))
       ([x y z & args] (f (apply g x y z args)))))
  ([f g & fs]
     (reduce1 comp (list* f g fs))))

(defn juxt
  "Takes a set of functions and returns a fn that is the juxtaposition
  of those fns.  The returned fn takes a variable number of args, and
  returns a vector containing the result of applying each fn to the
  args (left-to-right).
  ((juxt a b c) x) => [(a x) (b x) (c x)]"
  {:added "1.1"
   :static true}
  ([f]
     (fn
       ([] [(f)])
       ([x] [(f x)])
       ([x y] [(f x y)])
       ([x y z] [(f x y z)])
       ([x y z & args] [(apply f x y z args)])))
  ([f g]
     (fn
       ([] [(f) (g)])
       ([x] [(f x) (g x)])
       ([x y] [(f x y) (g x y)])
       ([x y z] [(f x y z) (g x y z)])
       ([x y z & args] [(apply f x y z args) (apply g x y z args)])))
  ([f g h]
     (fn
       ([] [(f) (g) (h)])
       ([x] [(f x) (g x) (h x)])
       ([x y] [(f x y) (g x y) (h x y)])
       ([x y z] [(f x y z) (g x y z) (h x y z)])
       ([x y z & args] [(apply f x y z args) (apply g x y z args) (apply h x y z args)])))
  ([f g h & fs]
     (let [fs (list* f g h fs)]
       (fn
         ([] (reduce1 #(conj %1 (%2)) [] fs))
         ([x] (reduce1 #(conj %1 (%2 x)) [] fs))
         ([x y] (reduce1 #(conj %1 (%2 x y)) [] fs))
         ([x y z] (reduce1 #(conj %1 (%2 x y z)) [] fs))
         ([x y z & args] (reduce1 #(conj %1 (apply %2 x y z args)) [] fs))))))

(defn partial
  "Takes a function f and fewer than the normal arguments to f, and
  returns a fn that takes a variable number of additional args. When
  called, the returned function calls f with args + additional args."
  {:added "1.0"
   :static true}
  ([f] f)
  ([f arg1]
   (fn
     ([] (f arg1))
     ([x] (f arg1 x))
     ([x y] (f arg1 x y))
     ([x y z] (f arg1 x y z))
     ([x y z & args] (apply f arg1 x y z args))))
  ([f arg1 arg2]
   (fn
     ([] (f arg1 arg2))
     ([x] (f arg1 arg2 x))
     ([x y] (f arg1 arg2 x y))
     ([x y z] (f arg1 arg2 x y z))
     ([x y z & args] (apply f arg1 arg2 x y z args))))
  ([f arg1 arg2 arg3]
   (fn
     ([] (f arg1 arg2 arg3))
     ([x] (f arg1 arg2 arg3 x))
     ([x y] (f arg1 arg2 arg3 x y))
     ([x y z] (f arg1 arg2 arg3 x y z))
     ([x y z & args] (apply f arg1 arg2 arg3 x y z args))))
  ([f arg1 arg2 arg3 & more]
   (fn [& args] (apply f arg1 arg2 arg3 (concat more args)))))

;;;;;;;;;;;;;;;;;;; sequence fns  ;;;;;;;;;;;;;;;;;;;;;;;

(defn sequence
  "Coerces coll to a (possibly empty) sequence, if it is not already
  one. Will not force a lazy seq. (sequence nil) yields (), When a
  transducer is supplied, returns a lazy sequence of applications of
  the transform to the items in coll(s), i.e. to the set of first
  items of each coll, followed by the set of second
  items in each coll, until any one of the colls is exhausted.  Any
  remaining items in other colls are ignored. The transform should accept
  number-of-colls arguments"
  {:added "1.0"
   :static true}
  ([coll]
     (if (seq? coll) coll
         (or (seq coll) ())))
  #_ (([xform coll]
       (or (clojure.lang.RT/chunkIteratorSeq
            (clojure.lang.TransformerIterator/create xform (clojure.lang.RT/iter coll)))
           ()))
      ([xform coll & colls]
       (or (clojure.lang.RT/chunkIteratorSeq
            (clojure.lang.TransformerIterator/createMulti
             xform
             (map #(clojure.lang.RT/iter %) (cons coll colls))))
           ()))))

(defn every?
  "Returns true if (pred x) is logical true for every x in coll, else
  false."
  {:tag Boolean
   :added "1.0"
   :static true}
  [pred coll]
  (cond
   (nil? (seq coll)) true
   (pred (first coll)) (recur pred (next coll))
   :else false))

(def
 ^{:tag Boolean
   :doc "Returns false if (pred x) is logical true for every x in
  coll, else true."
   :arglists '([pred coll])
   :added "1.0"}
 not-every? (comp not every?))

(defn some
  "Returns the first logical true value of (pred x) for any x in coll,
  else nil.  One common idiom is to use a set as pred, for example
  this will return :fred if :fred is in the sequence, otherwise nil:
  (some #{:fred} coll)"
  {:added "1.0"
   :static true}
  [pred coll]
    (when (seq coll)
      (or (pred (first coll)) (recur pred (next coll)))))

(def
 ^{:tag Boolean
   :doc "Returns false if (pred x) is logical true for any x in coll,
  else true."
   :arglists '([pred coll])
   :added "1.0"}
 not-any? (comp not some))

;will be redefed later with arg checks
(defmacro dotimes
  "bindings => name n
  Repeatedly executes body (presumably for side-effects) with name
  bound to integers from 0 through n-1."
  {:added "1.0"}
  [bindings & body]
  (let [i (first bindings)
        n (second bindings)]
    `(let [n# (clojure.lang.RT/longCast ~n)]
       (loop [~i 0]
         (when (< ~i n#)
           ~@body
           (recur (unchecked-inc ~i)))))))

(defn map
  "Returns a lazy sequence consisting of the result of applying f to
  the set of first items of each coll, followed by applying f to the
  set of second items in each coll, until any one of the colls is
  exhausted.  Any remaining items in other colls are ignored. Function
  f should accept number-of-colls arguments. Returns a transducer when
  no collection is provided."
  {:added "1.0"
   :static true}
  ([f]
    (fn [rf]
      (fn
        ([] (rf))
        ([result] (rf result))
        ([result input]
           (rf result (f input)))
        ([result input & inputs]
           (rf result (apply f input inputs))))))
  ([f coll]
   (lazy-seq
    (when-let [s (seq coll)]
      (cons (f (first s)) (map f (rest s))))))
  ([f c1 c2]
   (lazy-seq
    (let [s1 (seq c1) s2 (seq c2)]
      (when (and s1 s2)
        (cons (f (first s1) (first s2))
              (map f (rest s1) (rest s2)))))))
  ([f c1 c2 c3]
   (lazy-seq
    (let [s1 (seq c1) s2 (seq c2) s3 (seq c3)]
      (when (and  s1 s2 s3)
        (cons (f (first s1) (first s2) (first s3))
              (map f (rest s1) (rest s2) (rest s3)))))))
  ([f c1 c2 c3 & colls]
   (let [step (fn step [cs]
                 (lazy-seq
                  (let [ss (map seq cs)]
                    (when (every? identity ss)
                      (cons (map first ss) (step (map rest ss)))))))]
     (map #(apply f %) (step (conj colls c3 c2 c1))))))

(defmacro declare
  "defs the supplied var names with no bindings, useful for making forward declarations."
  {:added "1.0"}
  [& names] `(do ~@(map #(list 'def (vary-meta % assoc :declared true)) names)))

(declare cat)

(defn mapcat
  "Returns the result of applying concat to the result of applying map
  to f and colls.  Thus function f should return a collection. Returns
  a transducer when no collections are provided"
  {:added "1.0"
   :static true}
  ([f] (comp (map f) cat))
  ([f & colls]
     (apply concat (apply map f colls))))

(defn filter
  "Returns a lazy sequence of the items in coll for which
  (pred item) returns true. pred must be free of side-effects.
  Returns a transducer when no collection is provided."
  {:added "1.0"
   :static true}
  ([pred]
    (fn [rf]
      (fn
        ([] (rf))
        ([result] (rf result))
        ([result input]
           (if (pred input)
             (rf result input)
             result)))))
  ([pred coll]
   (lazy-seq
    (when-let [s (seq coll)]
      (if true #_(chunked-seq? s)
        #_(let [c (chunk-first s)
              size (count c)
              b (chunk-buffer size)]
          (dotimes [i size]
              (let [v (.nth c i)]
                (when (pred v)
                  (chunk-append b v))))
          (chunk-cons (chunk b) (filter pred (chunk-rest s))))
        (let [f (first s) r (rest s)]
          (if (pred f)
            (cons f (filter pred r))
            (filter pred r))))))))


(defn remove
  "Returns a lazy sequence of the items in coll for which
  (pred item) returns false. pred must be free of side-effects.
  Returns a transducer when no collection is provided."
  {:added "1.0"
   :static true}
  ([pred] (filter (complement pred)))
  ([pred coll]
     (filter (complement pred) coll)))

(defn reduced
  "Wraps x in a way such that a reduce will terminate with the value x"
  {:added "1.5"}
  [x]
  (throw "unimplemented")
  #_(clojure.lang.Reduced. x))

(defn reduced?
  "Returns true if x is the result of a call to reduced"
  {:inline (fn [x] `(clojure.lang.RT/isReduced ~x ))
   :inline-arities #{1}
   :added "1.5"}
  [x]
  (throw "unimplemented")
  #_(clojure.lang.RT/isReduced x))

(defn ensure-reduced
  "If x is already reduced?, returns it, else returns (reduced x)"
  {:added "1.7"}
  [x]
  (throw "unimplemented")
  #_(if (reduced? x) x (reduced x)))

(defn unreduced
  "If x is reduced?, returns (deref x), else returns x"
  {:added "1.7"}
  [x]
  (throw "unimplemented")
  #_(if (reduced? x) (deref x) x))

(defn take
  "Returns a lazy sequence of the first n items in coll, or all items if
  there are fewer than n.  Returns a stateful transducer when
  no collection is provided."
  {:added "1.0"
   :static true}
  #_([n]
     (fn [rf]
       (let [nv (volatile! n)]
         (fn
           ([] (rf))
           ([result] (rf result))
           ([result input]
              (let [n @nv
                    nn (vswap! nv dec)
                    result (if (pos? n)
                             (rf result input)
                             result)]
                (if (not (pos? nn))
                  (ensure-reduced result)
                  result)))))))
  ([n coll]
     (lazy-seq
      (when (pos? n)
        (when-let [s (seq coll)]
          (cons (first s) (take (dec n) (rest s))))))))

(defn take-while
  "Returns a lazy sequence of successive items from coll while
  (pred item) returns true. pred must be free of side-effects.
  Returns a transducer when no collection is provided."
  {:added "1.0"
   :static true}
  ([pred]
     (fn [rf]
       (fn
         ([] (rf))
         ([result] (rf result))
         ([result input]
            (if (pred input)
              (rf result input)
              (reduced result))))))
  ([pred coll]
     (lazy-seq
      (when-let [s (seq coll)]
        (when (pred (first s))
          (cons (first s) (take-while pred (rest s))))))))

(defn drop
  "Returns a lazy sequence of all but the first n items in coll.
  Returns a stateful transducer when no collection is provided."
  {:added "1.0"
   :static true}
  #_([n]
     (fn [rf]
       (let [nv (volatile! n)]
         (fn
           ([] (rf))
           ([result] (rf result))
           ([result input]
              (let [n @nv]
                (vswap! nv dec)
                (if (pos? n)
                  result
                  (rf result input))))))))
  ([n coll]
     (let [step (fn [n coll]
                  (let [s (seq coll)]
                    (if (and (pos? n) s)
                      (recur (dec n) (rest s))
                      s)))]
       (lazy-seq (step n coll)))))

(defn drop-last
  "Return a lazy sequence of all but the last n (default 1) items in coll"
  {:added "1.0"
   :static true}
  ([s] (drop-last 1 s))
  ([n s] (map (fn [x _] x) s (drop n s))))

(defn take-last
  "Returns a seq of the last n items in coll.  Depending on the type
  of coll may be no better than linear time.  For vectors, see also subvec."
  {:added "1.1"
   :static true}
  [n coll]
  (loop [s (seq coll), lead (seq (drop n coll))]
    (if lead
      (recur (next s) (next lead))
      s)))

(defn drop-while
  "Returns a lazy sequence of the items in coll starting from the
  first item for which (pred item) returns logical false.  Returns a
  stateful transducer when no collection is provided."
  {:added "1.0"
   :static true}
  #_([pred]
     (fn [rf]
       (let [dv (volatile! true)]
         (fn
           ([] (rf))
           ([result] (rf result))
           ([result input]
              (let [drop? @dv]
                (if (and drop? (pred input))
                  result
                  (do
                    (vreset! dv nil)
                    (rf result input)))))))))
  ([pred coll]
     (let [step (fn [pred coll]
                  (let [s (seq coll)]
                    (if (and s (pred (first s)))
                      (recur pred (rest s))
                      s)))]
       (lazy-seq (step pred coll)))))

(defn cycle
  "Returns a lazy (infinite!) sequence of repetitions of the items in coll."
  {:added "1.0"
   :static true}
  [coll] (clojure.lang.Cycle/create (seq coll)))

(defn split-at
  "Returns a vector of [(take n coll) (drop n coll)]"
  {:added "1.0"
   :static true}
  [n coll]
    [(take n coll) (drop n coll)])

(defn split-with
  "Returns a vector of [(take-while pred coll) (drop-while pred coll)]"
  {:added "1.0"
   :static true}
  [pred coll]
    [(take-while pred coll) (drop-while pred coll)])

(defn repeat
  "Returns a lazy (infinite!, or length n if supplied) sequence of xs."
  {:added "1.0"
   :static true}
  ([x] (clojure.lang.Repeat/create x))
  ([n x] (clojure.lang.Repeat/create n x)))

(defn replicate
  "DEPRECATED: Use 'repeat' instead.
   Returns a lazy seq of n xs."
  {:added "1.0"
   :deprecated "1.3"}
  [n x] (take n (repeat x)))

(defn iterate
  "Returns a lazy sequence of x, (f x), (f (f x)) etc. f must be free of side-effects"
  {:added "1.0"
   :static true}
  [f x] (clojure.lang.Iterate/create f x) )

(defn range
  "Returns a lazy seq of nums from start (inclusive) to end
  (exclusive), by step, where start defaults to 0, step to 1, and end to
  infinity. When step is equal to 0, returns an infinite sequence of
  start. When start is equal to end, returns empty list."
  {:added "1.0"
   :static true}
  ([]
   (iterate inc' 0))
  ([end]
   (clojure.lang.Range/create end))
  ([start end]
   (clojure.lang.Range/create start end))
  ([start end step]
   (clojure.lang.Range/create start end step)))

(defn merge
  "Returns a map that consists of the rest of the maps conj-ed onto
  the first.  If a key occurs in more than one map, the mapping from
  the latter (left-to-right) will be the mapping in the result."
  {:added "1.0"
   :static true}
  [& maps]
  (when (some identity maps)
    (reduce1 #(conj (or %1 {}) %2) maps)))

(defn merge-with
  "Returns a map that consists of the rest of the maps conj-ed onto
  the first.  If a key occurs in more than one map, the mapping(s)
  from the latter (left-to-right) will be combined with the mapping in
  the result by calling (f val-in-result val-in-latter)."
  {:added "1.0"
   :static true}
  [f & maps]
  (when (some identity maps)
    (let [merge-entry (fn [m e]
			(let [k (key e) v (val e)]
			  (if (contains? m k)
			    (assoc m k (f (get m k) v))
			    (assoc m k v))))
          merge2 (fn [m1 m2]
		   (reduce1 merge-entry (or m1 {}) (seq m2)))]
      (reduce1 merge2 maps))))



(defn zipmap
  "Returns a map with the keys mapped to the corresponding vals."
  {:added "1.0"
   :static true}
  [keys vals]
    (loop [map {}
           ks (seq keys)
           vs (seq vals)]
      (if (and ks vs)
        (recur (assoc map (first ks) (first vs))
               (next ks)
               (next vs))
        map)))

(defn line-seq
  "Returns the lines of text from rdr as a lazy sequence of strings.
  rdr must implement java.io.BufferedReader."
  {:added "1.0"
   :static true}
  [^java.io.BufferedReader rdr]
  (throw "unimplemented")
  #_(when-let [line (.readLine rdr)]
    (cons line (lazy-seq (line-seq rdr)))))

(defn comparator
  "Returns an implementation of java.util.Comparator based upon pred."
  {:added "1.0"
   :static true}
  [pred]
    (fn [x y]
      (cond (pred x y) -1 (pred y x) 1 :else 0)))

(defn sort
  "Returns a sorted sequence of the items in coll. If no comparator is
  supplied, uses compare.  comparator must implement
  java.util.Comparator.  Guaranteed to be stable: equal elements will
  not be reordered.  If coll is a Java array, it will be modified.  To
  avoid this, sort a copy of the array."
  {:added "1.0"
   :static true}
  ([coll]
   (sort compare coll))
  ([comp coll]
   (if (seq coll)
     (let [a (to-tuple coll)]
       #_(. java.util.Arrays (sort a comp))
       (lists/sort.e (seq a)))
     ())))

(defn sort-by
  "Returns a sorted sequence of the items in coll, where the sort
  order is determined by comparing (keyfn item).  If no comparator is
  supplied, uses compare.  comparator must implement
  java.util.Comparator.  Guaranteed to be stable: equal elements will
  not be reordered.  If coll is a Java array, it will be modified.  To
  avoid this, sort a copy of the array."
  {:added "1.0"
   :static true}
  ([keyfn coll]
   (sort-by keyfn compare coll))
  ([keyfn comp coll]
   (sort (fn [x y] (comp (keyfn x) (keyfn y))) coll)))

(defn dorun
  "When lazy sequences are produced via functions that have side
  effects, any effects other than those needed to produce the first
  element in the seq do not occur until the seq is consumed. dorun can
  be used to force any effects. Walks through the successive nexts of
  the seq, does not retain the head and returns nil."
  {:added "1.0"
   :static true}
  ([coll]
   (when-let [s (seq coll)]
     (recur (next s))))
  ([n coll]
   (when (and (seq coll) (pos? n))
     (recur (dec n) (next coll)))))

(defn doall
  "When lazy sequences are produced via functions that have side
  effects, any effects other than those needed to produce the first
  element in the seq do not occur until the seq is consumed. doall can
  be used to force any effects. Walks through the successive nexts of
  the seq, retains the head and returns it, thus causing the entire
  seq to reside in memory at one time."
  {:added "1.0"
   :static true}
  ([coll]
   (dorun coll)
   coll)
  ([n coll]
   (dorun n coll)
   coll))

(defn nthnext
  "Returns the nth next of coll, (seq coll) when n is 0."
  {:added "1.0"
   :static true}
  [coll n]
    (loop [n n xs (seq coll)]
      (if (and xs (pos? n))
        (recur (dec n) (next xs))
        xs)))

(defn nthrest
  "Returns the nth rest of coll, coll when n is 0."
  {:added "1.3"
   :static true}
  [coll n]
    (loop [n n xs coll]
      (if-let [xs (and (pos? n) (seq xs))]
        (recur (dec n) (rest xs))
        xs)))

(defn partition
  "Returns a lazy sequence of lists of n items each, at offsets step
  apart. If step is not supplied, defaults to n, i.e. the partitions
  do not overlap. If a pad collection is supplied, use its elements as
  necessary to complete last partition upto n items. In case there are
  not enough padding elements, return a partition with less than n items."
  {:added "1.0"
   :static true}
  ([n coll]
     (partition n n coll))
  ([n step coll]
     (lazy-seq
       (when-let [s (seq coll)]
         (let [p (doall (take n s))]
           (when (= n (count p))
             (cons p (partition n step (nthrest s step))))))))
  ([n step pad coll]
     (lazy-seq
       (when-let [s (seq coll)]
         (let [p (doall (take n s))]
           (if (= n (count p))
             (cons p (partition n step pad (nthrest s step)))
             (list (take n (concat p pad)))))))))

;;;;;;;;;;;;

(defmacro defn-
  "same as defn, yielding non-public def"
  {:added "1.0"}
  [name & decls]
  (list* `defn (with-meta name (assoc (meta name) :private true)) decls))

(defn subs
  "Returns the substring of s beginning at start inclusive, and ending
  at end (defaults to length of string), exclusive."
  {:added "1.0"
   :static true}
  ([s start] (subs s start (erlang/size.e s)))
  ([s start end] (binary/part.e s start (- end start))))

(defn- root-resource
  "Returns the root directory path for a lib"
  {:tag String}
  [lib]
  (str "/"
       (binary/replace.e (name lib) "." "/" (seq [:global]))))

(defn- last-index-of [s x]
  (let [matches (binary/matches.e s x)]
    (if (seq matches)
      (first (last matches))
      -1)))

(defn- root-directory
  "Returns the root resource path for a lib"
  [lib]
  (let [d (root-resource lib)]
    (subs d 0 (last-index-of d "/"))))

(defn load
  "Loads Clojure code from resources in the code path. A path is interpreted
  as code path-relative if it begins with a slash or relative to the root
  directory for the current namespace otherwise."
  [& paths]
  (loop [paths paths]
    (when paths
      (let [path (first paths)
            path (if (clojerl.String/starts_with.e path "/")
                   path
                   (throw "unimplemented")
                   #_(str (root-directory (name *ns*)) "/" path))]
        (clj_core/load.e (subs path 1))
        (recur (next paths))))))

(defn- load-libs
  [& libs]
  (loop [libs libs]
    (when libs
      (load (root-resource (first libs)))
      (recur (next libs)))))

(defmacro require
  "Loads libs, skipping any that are already loaded. Each argument is
  either a libspec that identifies a lib, a prefix list that identifies
  multiple libs whose names share a common prefix, or a flag that modifies
  how all the identified libs are loaded. Use :require in the ns macro
  in preference to calling this directly.
  Libs
  A 'lib' is a named set of resources in classpath whose contents define a
  library of Clojure code. Lib names are symbols and each lib is associated
  with a Clojure namespace and a Java package that share its name. A lib's
  name also locates its root directory within classpath using Java's
  package name to classpath-relative path mapping. All resources in a lib
  should be contained in the directory structure under its root directory.
  All definitions a lib makes should be in its associated namespace.
  'require loads a lib by loading its root resource. The root resource path
  is derived from the lib name in the following manner:
  Consider a lib named by the symbol 'x.y.z; it has the root directory
  <classpath>/x/y/, and its root resource is <classpath>/x/y/z.clj. The root
  resource should contain code to create the lib's namespace (usually by using
  the ns macro) and load any additional lib resources.
  Libspecs
  A libspec is a lib name or a vector containing a lib name followed by
  options expressed as sequential keywords and arguments.
  Recognized options:
  :as takes a symbol as its argument and makes that symbol an alias to the
    lib's namespace in the current namespace.
  :refer takes a list of symbols to refer from the namespace or the :all
    keyword to bring in all public vars.
  Prefix Lists
  It's common for Clojure code to depend on several libs whose names have
  the same prefix. When specifying libs, prefix lists can be used to reduce
  repetition. A prefix list contains the shared prefix followed by libspecs
  with the shared prefix removed from the lib names. After removing the
  prefix, the names that remain must not contain any periods.
  Flags
  A flag is a keyword.
  Recognized flags: :reload, :reload-all, :verbose
  :reload forces loading of all the identified libs even if they are
    already loaded
  :reload-all implies :reload and also forces loading of all libs that the
    identified libs directly or indirectly load via require or use
  :verbose triggers printing information about each load, alias, and refer
  Example:
  The following would load the libraries clojure.zip and clojure.set
  abbreviated as 's'.
  (require '(clojure zip [set :as s]))"
  {:added "1.0"}

  [& args]
  (apply load-libs #_ :require args))

;;------------------------------------------------------------------------------
;;------------------------------------------------------------------------------

(defn prn
  [& xs]
  (io/format.e "~s~n" (seq [(apply str xs)])))

(defn =
  [a b]
  (erlang/==.2 a b))

(defmacro assert
  [v]
  (when (not v) (throw :assert)))