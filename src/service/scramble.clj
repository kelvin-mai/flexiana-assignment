(ns service.scramble)

(defn s->freq-map
  "takes string and transform it into a map of char and it's frequency"
  [s] (reduce (fn [acc char]
                (update acc char (fnil inc 0)))
              {} s))

(defn scramble? [s1 s2]
  (let [fmap1 (s->freq-map s1)
        fmap2 (s->freq-map s2)]
    (every? (fn [[k v]] (>= (get fmap1 k 0) v))
            fmap2)))

