(ns benchmark.benchmark-runner
  (:require [clojure.core.reducers :as r]
            [clojure.string :as string]))

(defrecord Foo [bar baz])

#?(:clj
   (defmacro simple-benchmark
     "Runs expr iterations times in the context of a let expression with
  the given bindings, then prints out the bindings and the expr
  followed by number of iterations and total time. The optional
  argument print-fn, defaulting to println, sets function used to
  print the result. expr's string representation will be produced
  using pr-str in any case."
     {:added "1.0"}
     [bindings expr iterations & {:keys [print-fn] :or {print-fn 'println}}]
     (let [bs-str   (pr-str bindings)
           expr-str (pr-str expr)]
       `(let ~bindings
          (let [start#   (System/nanoTime)
                ret#     (dotimes [_# ~iterations] ~expr)
                end#     (System/nanoTime)
                elapsed# (int (/ (- end# start#) 1000000))]
            (~print-fn (str ~bs-str ", " ~expr-str ", "
                            ~iterations " runs, " elapsed# " msecs")))))))

#?(:clj
   (defn vm-info []
     (System/getProperty "java.version"))
   :clje
   (defn vm-info []
     (->> [:otp_release :version]
          (map (comp erlang/list_to_binary.1
                     erlang/system_info.1))
          (apply format "Erlang/OTP ~s [erts-~s]"))))

(def strings
  (into [] (take 10 (iterate (fn [s] (str s "string")) "string"))))

(def big-str-data
  (pr-str {:nils (repeat 10 nil)
           :bools (concat (repeat 5 false) (repeat 5 true))
           :ints (range 10000 10100)
           :floats (map #(float (/ % 7)) (range 0 100))
           :keywords (map keyword strings)
           :symbols (map symbol strings)
           :strings strings}))

(defn ints-seq
  ([n] (ints-seq 0 n))
  ([i n]
   (when (< i n)
     (lazy-seq
      (cons i (ints-seq (inc i) n))))))

(defmulti simple-multi identity)
(defmethod simple-multi :foo [x] x)

(defn -main [& args]
  (println ";;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;")
  (println ";;; VM      = " (vm-info))
  (println ";;; Clojure = " (clojure-version))
  (println ";;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;\n")

  (simple-benchmark [x 1] (identity x) 1000000)

  (println ";; symbol construction")
  (simple-benchmark [] (symbol 'foo) 1000000)
  (println)

  (println ";;; instance?")
  (simple-benchmark [coll []] (instance? #?(:clje clojerl.Vector :clj clojure.lang.PersistentVector) coll) 1000000)
  (println ";;; satisfies?")
  (simple-benchmark [coll (list 1 2 3)] (#?(:clje satisfies? :clj instance?) #?(:clje clojerl.ISeq :clj clojure.lang.ISeq) coll) 1000000)
  (simple-benchmark [coll [1 2 3]] (#?(:clje satisfies? :clj instance?) #?(:clje clojerl.ISeq :clj clojure.lang.ISeq) coll) 1000000)
  (println)

  (println ";;; tuple & string ops")
  (simple-benchmark [coll "foobar"] (seq coll) 1000000)
  (simple-benchmark [coll "foobar"] (first coll) 1000000)
  (simple-benchmark [coll "foobar"] (nth coll 2) 1000000)
  (simple-benchmark [coll #?(:clje (tuple 1 2 3) :clj (int-array [1 2 3]))] (seq coll) 1000000)
  (simple-benchmark [coll #?(:clje (tuple 1 2 3) :clj (int-array [1 2 3]))] (first coll) 1000000)
  (simple-benchmark [coll #?(:clje (tuple 1 2 3) :clj (int-array [1 2 3]))] (nth coll 2) 1000000)
  (println)

  (println ";;; list ops")
  (simple-benchmark [coll (list 1 2 3)] (first coll) 1000000)
  (simple-benchmark [coll (list 1 2 3)] (rest coll) 1000000)
  (simple-benchmark [] (list) 1000000)
  (simple-benchmark [] (list 1 2 3) 1000000)
  (println)

  (println ";;; vector ops")
  (simple-benchmark [] [] 1000000)
  (simple-benchmark [[a b c] (take 3 (repeatedly #(rand-int 10)))] (count [a b c]) 1000000)
  (simple-benchmark [[a b c] (take 3 (repeatedly #(rand-int 10)))] (count (vec [a b c])) 1000000)
  (simple-benchmark [[a b c] (take 3 (repeatedly #(rand-int 10)))] (count (vector a b c)) 1000000)
  (simple-benchmark [coll [1 2 3]] (nth coll 0) 1000000)
  (simple-benchmark [coll [1 2 3]] (coll 0) 1000000)
  (simple-benchmark [coll [1 2 3]] (conj coll 4) 1000000)
  (simple-benchmark [coll [1 2 3]] (seq coll) 1000000)
  (simple-benchmark [coll (seq [1 2 3])] (first coll) 1000000)
  (simple-benchmark [coll (seq [1 2 3])] (rest coll) 1000000)
  (simple-benchmark [coll (seq [1 2 3])] (next coll) 1000000)
  (println)

  (println ";;; large vector ops")
  (simple-benchmark [] (reduce conj [] (range 40000)) 10)
  (simple-benchmark [coll (reduce conj [] (range (+ 32768 32)))] (conj coll :foo) 100000)
  (simple-benchmark [coll (reduce conj [] (range 40000))] (assoc coll 123 :foo) 100000)
  (simple-benchmark [coll (reduce conj [] (range (+ 32768 33)))] (pop coll) 100000)
  (println)

  (println ";;; vector equality")
  (simple-benchmark
   [a (into [] (range 1000000))
    b (into [] (range 1000000))]
   (= a b) 1)
  (println)

  (println ";;; keyword compare")
  #_(let [seed ["amelia" "olivia" "jessica" "emily" "lily" "ava" "isla" "sophie" "mia" "isabella" "evie" "poppy" "ruby" "grace" "sophia" "chloe" "freya" "isabelle" "ella" "charlotte" "scarlett" "daisy" "lola" "holly" "eva" "lucy" "millie" "phoebe" "layla" "maisie" "sienna" "alice" "florence" "lilly" "ellie" "erin" "elizabeth" "imogen" "summer" "molly" "hannah" "sofia" "abigail" "jasmine" "matilda" "megan" "rosie" "lexi" "lacey" "emma" "amelie" "maya" "gracie" "emilia" "georgia" "hollie" "evelyn" "eliza" "amber" "eleanor" "bella" "amy" "brooke" "leah" "esme" "harriet" "anna" "katie" "zara" "willow" "elsie" "annabelle" "bethany" "faith" "madison" "isabel" "rose" "julia" "martha" "maryam" "paige" "heidi" "maddison" "niamh" "skye" "aisha" "mollie" "ivy" "francesca" "darcey" "maria" "zoe" "keira" "sarah" "tilly" "isobel" "violet" "lydia" "sara" "caitlin"]]
      (simple-benchmark
       [arr (into-tuple (repeatedly 10000 #(keyword (rand-nth seed))))]
       (.sort arr compare)
       100)
      (simple-benchmark
       [arr (into-tuple (repeatedly 10000 #(keyword (rand-nth seed) (rand-nth seed))))]
       (.sort arr compare)
       100))
  (println)

  (println ";;; reduce lazy-seqs, vectors, ranges")
  (simple-benchmark [coll (take 100000 (iterate inc 0))] (reduce + 0 coll) 1)
  (simple-benchmark [coll (range 1000000)] (reduce + 0 coll) 1)
  (simple-benchmark [coll (into [] (range 1000000))] (reduce + 0 coll) 1)
  (println)

  (println ";; apply")
  (simple-benchmark [coll (into [] (range 1000000))] (apply + coll) 1)
  (simple-benchmark [] (list 1 2 3 4 5) 1000000)
  ;; (simple-benchmark [xs (array-seq (array 1 2 3 4 5))] (apply list xs) 1000000)
  (simple-benchmark [xs (list 1 2 3 4 5)] (apply list xs) 1000000)
  (simple-benchmark [xs [1 2 3 4 5]] (apply list xs) 1000000)
  (simple-benchmark [f (fn [a b & more])] (apply f (range 32)) 1000000)
  (simple-benchmark [f (fn [a b c d e f g h i j & more])] (apply f (range 32)) 1000000)
  (println)

  (println ";; update-in")
  (simple-benchmark [coll {:foo 1} ks [:foo]] (update-in coll ks inc) 1000000)
  ;; (simple-benchmark [coll (array-map :foo 1) ks [:foo]] (update-in coll ks inc) 1000000)
  (println)

  (println ";;; map / record ops")
  (simple-benchmark [coll {:foo 1 :bar 2}] (get coll :foo) 1000000)
  (simple-benchmark [coll {'foo 1 'bar 2}] (get coll 'foo) 1000000)
  (simple-benchmark [coll {:foo 1 :bar 2}] (:foo coll) 1000000)
  (simple-benchmark [coll {'foo 1 'bar 2}] ('foo coll) 1000000)
  (let [kw  :foo
        sym 'foo]
    (simple-benchmark [coll {:foo 1 :bar 2}] (kw coll) 1000000)
    (simple-benchmark [coll {'foo 1 'bar 2}] (sym coll) 1000000))
  (simple-benchmark [coll {:foo 1 :bar 2}]
                    (loop [i 0 m coll]
                      (if (< i 100000)
                        (recur (inc i) (assoc m :foo 2))
                        m))
                    1)

  (simple-benchmark [coll (new Foo 1 2)] (:bar coll) 1000000)
  (simple-benchmark [coll (new Foo 1 2)] (assoc coll :bar 2) 1000000)
  (simple-benchmark [coll (new Foo 1 2)] (assoc coll :baz 3) 1000000)
  (simple-benchmark [coll (new Foo 1 2)]
                    (loop [i 0 m coll]
                      (if (< i 1000000)
                        (recur (inc i) (assoc m :bar 2))
                        m))
                    1)
  (println)

  (println ";;; zipmap")
  (simple-benchmark [m {:a 1 :b 2 :c 3}] (zipmap (keys m) (map inc (vals m))) 100000)
  (println)

  (println ";;; seq ops")
  (simple-benchmark [coll (range 500000)] (reduce + coll) 1)
  (println)

  (println ";;; reader")
  (simple-benchmark [s "{:foo [1 2 3]}"] (read-string s) 1000)
  (simple-benchmark [s big-str-data] (read-string s) 1000)
  (println)

  (println ";;; range")
  (simple-benchmark [r (range 1000000)] (last r) 1)
  (println)

  (let [r (ints-seq 1000000)]
    (println ";;; lazy-seq")
    (println ";;; first run")
    (simple-benchmark [r r] (last r) 1)
    (println ";;; second run")
    (simple-benchmark [r r] (last r) 1)
    (println))

  (println ";;; comprehensions")
  (simple-benchmark [xs (range 512)] (last (for [x xs y xs] (+ x y))) 1)
  (simple-benchmark [xs (vec (range 512))] (last (for [x xs y xs] (+ x y))) 4)
  (println)

  (println ";; reducers")
  (simple-benchmark [xs (into [] (range 1000000))] (r/reduce + (r/map inc (r/map inc (r/map inc xs)))) 1)

  (println ";; transducers")
  (simple-benchmark [xs (into [] (range 1000000))] (transduce (comp (map inc) (map inc) (map inc)) + 0 xs) 1)

  (println ";; reduce range 1000000 many ops")
  (simple-benchmark [xs (range 1000000)] (reduce + 0 (map inc (map inc (map inc xs)))) 1)

  (println ";; transduce range 1000000 many ops ")
  (simple-benchmark [xs (range 1000000)] (transduce (comp (map inc) (map inc) (map inc)) + 0 xs) 1)

  (println "\n")

  (println ";; multimethods")
  (simple-benchmark [] (simple-multi :foo) 1000000)
  (println "\n")

  (println ";; higher-order variadic function calls")
  ;; Deliberately frustrates static-fn optimization and macros
  (simple-benchmark [f #?(:clje tuple :clj int-array)] #?(:clje (f 1 2 3 4 5 6 7 8 9 0) :clj (f [1 2 3 4 5 6 7 8 9 0])) 100000)
  (simple-benchmark [f vector] (f 1 2 3 4 5 6 7 8 9 0) 100000)
  (simple-benchmark [] (= 1 1 1 1 1 1 1 1 1 0) 100000)

  (println "\n")
  (println ";; Destructuring a sequence")
  (simple-benchmark [v (into [] (range 1000000))]
                    (loop [[x & xs] v]
                      (if-not (nil? xs)
                        (recur xs)
                        x))
                    10)

  (println "\n")
  (println ";;; str")
  (simple-benchmark [] (str 1) 1000000)
  (simple-benchmark [] (str nil) 1000000)
  (simple-benchmark [] (str "1") 1000000)
  (simple-benchmark [] (str "1" "2") 1000000)
  (simple-benchmark [] (str "1" "2" "3") 1000000)

  (println "\n")
  (println ";;; clojure.string")
  (simple-benchmark [s "a" f clojure.string/capitalize] (f s) 1000000)
  (simple-benchmark [s "aBcDeF" f clojure.string/capitalize] (f s) 1000000)

  (println ";; printing of numbers")
  (simple-benchmark [x true] (pr-str x) 1000)
  (simple-benchmark [x 10] (pr-str x) 1000)

  (println "\n")
  (println ";; cycle")
  (simple-benchmark [] (doall (take 1000 (cycle [1 2 3]))) 1000)
  (simple-benchmark [] (into [] (take 1000) (cycle [1 2 3])) 1000)
  (simple-benchmark [] (reduce + (take 64 (cycle [1 2 3]))) 10000)
  (simple-benchmark [] (transduce (take 64) + (cycle [1 2 3])) 10000)

  (println "\n")
  (println ";; repeat")
  (simple-benchmark [] (doall (take 1000 (repeat 1))) 1000)
  (simple-benchmark [] (into [] (take 1000) (repeat 1)) 1000)
  (simple-benchmark [] (doall (repeat 1000 1)) 1000)
  (simple-benchmark [] (into [] (repeat 1000 1)) 1000)
  (simple-benchmark [] (reduce + 0 (repeat 1000 1)) 1000)
  (simple-benchmark [] (into [] (take 1000) (repeat 1)) 1000)
  (simple-benchmark [] (reduce + (take 64 (repeat 1))) 10000)
  (simple-benchmark [] (transduce (take 64) + (repeat 1)) 10000)
  (simple-benchmark [] (reduce + (take 64 (repeat 48 1))) 10000)
  (simple-benchmark [] (transduce (take 64) + (repeat 48 1)) 10000)

  (println "\n")
  (println ";; iterate")
  (simple-benchmark [] (doall (take 1000 (iterate inc 0))) 1000)
  (simple-benchmark [] (into [] (take 1000) (iterate inc 0)) 1000)
  (simple-benchmark [] (reduce + (take 64 (iterate inc 0))) 10000)
  (simple-benchmark [] (transduce (take 64) + (iterate inc 0)) 10000)
  (println)
  )

#?(:clj (-main))
