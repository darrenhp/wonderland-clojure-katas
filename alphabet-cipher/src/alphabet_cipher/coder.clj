(ns alphabet-cipher.coder)

(defn encode [keyword message]
  (apply str
         (map #(char (+ (rem (+ (- (int %2) (int \a))
                                (- (int %1) (int \a))
                                26)
                             26)
                        (int \a)))
              (cycle keyword)
              message)))

(defn decode [keyword message]
  (apply str
         (map #(char (+ (rem (+ (- (- (int %2) (int \a))
                                   (- (int %1) (int \a)))
                                26)
                             26)
                        (int \a)))
              (cycle keyword)
              message)))

(defn decipher [cipher message]
  (apply str
         (let [xs (map #(char (+ (rem (+ (- (int %1)
                                            (int %2))
                                         26)
                                      26)
                                 (int \a)))
                       cipher
                       message),
               xslen (count xs)]
           (loop [n 1]
             (if (= (take xslen
                          (cycle (take n xs)))
                    xs)
               (take n xs)
               (recur (+ n 1)))))))


