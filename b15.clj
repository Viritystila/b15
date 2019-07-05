(ns b15 (:use [trigger.trigger] [trigger.synths] [trigger.algo] [overtone.core]) (:require [viritystone.tone :as t]) )



(defn fast [factor input] (vec (repeat factor (seq input ) )))

(defn slow [factor input] (map vec (partition factor input)))


(def beat-fobm_2 {
           [0.125] { 0.125 0.91  0.25 0.06  0.5 0.9   0.0625 0.9 }
           [0.25] {  0.125 0.92  0.25 0.05  0.5 0.9   0.0625 0.9 }
           [0.5] {   0.125 0.7   0.25 0.9   0.5 0.09  0.0625 0.9}
           [0.0625] {  0.125 0.09  0.25 0.04  0.5 0.01  0.0625 0.9}
           })

(trg :kick kick :in-trg  (fast 16 [1 1]) [(repeat 8 1)] [1 1 1 1]  [1 1 1 [ r r r 1] ] [1 [1 1]] [1 1])


(trg :tom1
     tom
     :in-trg  [1]
     :in-stick-level [0.5]
     :in-amp [0.3])

(alg :tom1 :in-trg 0 example_markov beat-fobm_2)

(stpa)



(t/start "./b15.glsl" :width 1920 :height 1080 :cams [0 1] :videos ["../videos/soviet1.mp4" "../videos/uni_fixed.mp4" "../videos/soviet4.mp4" "../videos/spede_fixed.mp4"])
