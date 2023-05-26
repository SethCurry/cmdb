(ns cmdb.models)

(defmacro defmodel
  {:clj-kondo/ignore [:unresolved-symbol :type-mismatch]}
  [n r]
  (list 'do
        (list 'def (symbol (str "coll-" (symbol n))) (list 'atom '[]))
        (list 'defrecord (symbol n) (into [] (map symbol r)))
        (list 'defn (symbol (str "create-" (name n)))
              (into [] (map symbol r)) (list 'let (vector (symbol "item") (into (map symbol r) [(symbol (str "->" (name n)))]))
                                             (list 'swap! (symbol (str "coll-" (name n))) 'conj (symbol "item"))))))
