(ns models)

(defmacro model-coll
  {:clj-kondo/ignore [:unresolved-symbol :type-mismatch]}
  [n]
  (list 'symbol (list 'str "coll-" (symbol n))))

(defmacro defmodel
  {:clj-kondo/ignore [:unresolved-symbol :type-mismatch]}
  [n r]
  (list 'do
        (list 'def (model-coll n) (list 'atom '[]))
        (list 'defrecord (symbol n) (into [] (map symbol r)))
        (list 'defn (symbol (str "create-" (name n)))
              (into [] (map symbol r)) (list 'let (vector (symbol "item") (into (map symbol r) [(symbol (str "->" (name n)))]))
                                             (list 'swap! (model-coll n) 'conj (symbol "item"))))))
