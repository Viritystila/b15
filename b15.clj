(ns b15
  (:use [trigger.trigger]
        [trigger.synths]
        [trigger.algo]
        [trigger.speech]
        [trigger.samples] [overtone.core]) (:require [viritystone.tone :as t]))

(require '[trigger.insts :refer :all])

(future
  (println "Begin loading SuperDirt samples")
  (load-all-SuperDirt-samples)
  (println "Samples loaded"))

(def bf2 (atom{}))

(defn add-tts-sample [name path nosamples]
  (future
    (println "Begin loading sample " name)
    (add-sample name (string-to-buffer (generate-markov-text path nosamples)))
    (println "Sample" name "loaded") ))


(add-sample "bstring" (string-to-buffer "Bellybutton"))

(generate-markov-text "kalevala.txt" 60)


(add-sample "d" (string-to-buffer (generate-markov-text "kalevala.txt" 10)))


(add-sample "d1" (string-to-buffer (generate-markov-text "kalevala.txt" 10)))


(add-sample "d2" (string-to-buffer (generate-markov-text "kalevala.txt" 10)))


(add-sample "d3" (string-to-buffer (generate-markov-text "kalevala.txt" 10)))


(add-sample "e" (string-to-buffer (generate-markov-text "paradiselost.txt" 1)))


(add-sample "f" (string-to-buffer (generate-markov-text "generalrelativity.txt" 1)))


(add-sample "g" (string-to-buffer (generate-markov-text "daq.txt" 1)))

(def kal (map (fn [x] (generate-markov-text "paradiselost.txt" x)) (repeat 8 1) ))

(def kals (map (fn [x] (string-to-buffer x)) kal))

(doall (map (fn [x]  )))

(add-tts-sample "h"    "generalparadisedaq.txt" 200)


(add-tts-sample "i"   "generalparadiselost.txt"  200)


(add-tts-sample "j"   "generalparadisedaqx2.txt" 200)


(add-tts-sample "k"  "generalx2paradisedaqx2.txt" 200)


(trg :sampl trg-sampler_i
     :in-trg  [r]
     :in-buf ["d"]; ["g"] (repeat 4 ["f"]) (repeat 4 ["e"])
     :in-loop [1]
     :in-step (rep 7 [2])(slw 1 [(sir 32 2.5 1 32)])
     :in-amp [1.0])

(trg :sampl2 trg-sampler_i
     :in-trg
     ;(fst [(rep 8 "e")])
     ;(fst [(rep 8 "g")])
     ;[["g" "f"] [r r r ["g" [(rep 1 "f")]]]]
     ;[ (sfl (fll 16 [["g"] ["f" "e"] [r]]))]
     ;[(rep 8 "f")]
     ;[(rep 8 "e")]

     ["d"] (rep 3 [r])(rep 3 [(rep 2 "d")])
     ["d1"] (rep 3 [r]); (rep 3 [(rep 16 "d2")])
     ["d2"] (rep 3 [r])
     ["d3"] (rep 3 [r]);(rep 3 (fst 4 ["d1" ["d2" "d3"]]))
     :in-buf ":in-trg"
     :in-loop [0]
     :in-start-pos [0]
     :in-step [2] [1.5] [2] [2.5] ;[-2]                    ; (slw 8 [(sir 32 2.5 1 32)])
     :in-amp [1.0])


(trg :sampl3 trg-sampler_i
     :in-trg  [r]
     :in-buf ["e"]; ["g"] (repeat 4 ["f"]) (repeat 4 ["e"])
     :in-loop [1]
     :in-step  [2.0]              ;(map vec (partition 1 (range -4 4 0.5)))
     :in-amp [1])

(map vec (partition 1 (range 0.5 10)))

(fx! :sampl fx-echo)

(fx! :sampl2 fx-feedback)

(clrfx! :sampl2)

(stp :sampl2)

(stp :sampl3)

(lss)

(sta)

(reset! bf2  {
                     [0.125] { 0.125 0.91  0.25 0.06  0.5 0.099   0.0625 0.9 }
                     [0.25] {  0.125 0.92  0.25 0.05  0.5 0.099   0.0625 0.9 }
                     [0.5] {   0.125 0.07   0.25 0.09   0.5 0.09  0.0625 0.9}
                     [0.0625] {  0.125 0.9  0.25 0.04  0.5 0.01  0.0625 0.9}
                     } )

(reset! bf2  {[0.0625] {0.0625 0.5 0.125 0.5}
              [0.125 ] {0.0625 0.5 0.125 0.5}})

(sta)

(list-samples)

(contains? @samplePool :bd1)

(count @samplePool)

(dirt "bd" 3)


(lss)

(stp :bow2)

;Brekakbeat

(set-pattern-duration (/ 1 0.5625))

(trg :kick2 kick2_i :in-trg (rep 3 (del 1 1 (del 0 3 [1 1 ]))) [[1 1 1 r r r r r] 1]
      (rep 3 (del 1 1 (del 0 3 [1 1 ])))  [[r r r 1] [r 1 1 r r r r r]]
     :in-amp [1])



(trg :kick2 kick2_i :in-trg  [[1 1 1 r r r r r] 1]
       [(rep 16 [r 1] )]
       [[r r r 1] [r 1 1 r r r r r]]
     :in-amp [2])


(trg :kick2 kick2_i :in-trg   (seq (fll 4 [[1] [r r r 1 1 r r r]]))
     [1] [1 [1 1] r r 1 1 [1 1 1 1] r]
     [1] [1 1 r r r r 1 [1 1 1 1]]
     [[1 1] r r] [(rep 16 1)]
     :in-amp [1])


(trg :kick2 kick2_i
     :in-trg [1] [[1 1] r r r] [r r [r r r 1] 1]
     :in-amp [1])


(trg :kick2 kick2_i :in-trg [1])

(sta)

(stp :kick2)

;;;;
                                        ;General paradise lost
;;;;

(add-tts-sample "k"  "generalx2paradisedaqx2.txt" 200)


(trg :sampl trg-sampler_i
     :in-trg  [r]
     :in-buf ["k"]; ["g"] (repeat 4 ["f"]) (repeat 4 ["e"])
     :in-loop [1]
     :in-step (rep 7 [2])(slw 1 [(sir 32 2.5 1 32)])
     :in-amp [0.2])


(trg :nh hat2_i
     :in-trg  (rep 3 [1 1]) [1 [1 1 r r] 1 [r r 1 1]]
     (rep 3 [1 1]) [ [(rep 16 1)]  [(rep 4 1 )] [(rep 64 1)] 1]
     (rep 3 [1 1]) [1 [1 1 1 1 1 r r r ] [r 1] r]
     (rep 3 [1 1]) [1 [1 1 1 1 1 r r r ] [r [1 1]] r]
     :in-amp [0.2]
     :in-decay  (rep 3 [0.2]) [0.5] ; [[0.1 0.1 0.1 0.1 0.1] r r r [1 1 1 1] r r r r]
     )


(trg :nh hat2_i
     :in-trg  [(rep 8 1)]
     [(rep 16 1)]
     [[(rep 16 1)]
      [(rep 4 1 )]
      [(rep 64 1)] 1]
     [r]
     :in-amp [0.5]
     :in-decay  [0.3] [(range 0.1 1 0.1)] [(range 0.1 1 0.1)] ; [[0.1 0.1 0.1 0.1 0.1] r r r [1 1 1 1] r r r r]q
     )

(stp :nh)

(lss)

(sta)

(set-pattern-duration 2)
(set-pattern-delay 1.40)


;hyvää bassoa
(trg :vb
     vintage-bass_i
     :in-trg   ; [(rep 6 "a4")]
                                        ;(vec (repeat 2 (seq ["b4" "b4" "b4" ["e4" ["e4" "d4"]] ])))
                                        ;[["a4" "d4"] "e4" "b4" "b4"]
     [["b4" "e4"] "b4" "b4" ["e4" ["e4" "d4"]] ]
     ;(repeat  3 [r])
     ;["a5"]
     ;["b5"]
     ;["d5"]
     ;["e5" "d3" "b2" "b4"]
     :in-gate-select   (rep 4 [1]) (rep 4 [0])
     :in-amp [1.4]
     :in-note  ":in-trg"
     :in-a [0.001]
     :in-d [0.93]
     :in-s [0.95]
     :in-r [0.35])

(fx! :vb fx-distortion2)
(fx! :vb fx-reverb)
(clrfx! :vb)


(stp :vb)


;;;;
;;;;


;;;;
;;;; Sample exists

(add-sample "d" (string-to-buffer (generate-markov-text "kalevala.txt" 10)))


(add-sample "d1" (string-to-buffer (generate-markov-text "kalevala.txt" 10)))


(add-sample "d2" (string-to-buffer (generate-markov-text "kalevala.txt" 10)))


(add-sample "d3" (string-to-buffer (generate-markov-text "kalevala.txt" 10)))



(trg :sampl2 trg-sampler_i
     :in-trg
     ;(fst [(rep 8 "e")])
     ;(fst [(rep 8 "g")])
     ;[["g" "f"] [r r r ["g" [(rep 1 "f")]]]]
     ;[ (sfl (fll 16 [["g"] ["f" "e"] [r]]))]
     ;[(rep 8 "f")]
     ;[(rep 8 "e")]

     ["d"] (rep 3 [r])(rep 3 [(rep 2 "d")])
     ["d1"] (rep 3 [r]); (rep 3 [(rep 16 "d2")])
     ["d2"] (rep 3 [r])
     ["d3"] (rep 3 [r]);(rep 3 (fst 4 ["d1" ["d2" "d3"]]))
     :in-buf ":in-trg"
     :in-loop [0]
     :in-start-pos [0]
     :in-step [2] [1.5] [2] [2.5] ;[-2]                    ; (slw 8 [(sir 32 2.5 1 32)])
     :in-amp [1.0])



(trg :op overpad_i
     :in-trg  (rep 3 [ "e2"]) ["b2"]
     (rep 7 ["a2" ])
     (rep 2 ["d3"]) ["b2"]
     :in-note  ":in-trg"
     :in-gate-select [1]
     :in-attack [0.001]
     :in-decay  [1.1]
     :in-sustain [1.01]
     :in-release [0.35]
     :in-amp [0.19])

(fx! :op fx-distortion2)

(clrfx! :op)

(stp :op)




(trg :bow2
     bowed_i
     :in-trg  [1]; (rep 8 (fll 32 [1 0]) ) (rep 4  (fll 16 [1 [0 1]]))
     :in-amp [0.81]
     :in-note  ["a2"]
    ["a2" "c2"]
    ["b2"]
    ["b2" "d2"]
    ["a3"]
    ["a3" "f2"]
    ["e2"]
    ["e2" "b2"]
     ;(trigger.algo/chd :i :g)
     :in-gate-select [0]
     :in-bow-offset [0.001]
     :in-bow-position [0.0075]
     :in-bow-slope [0.08]
     :in-vib-freq [0.0127]
     :in-vib-gain [0.019] )


(fx! :bow2 fx-reverb)

(fx! :bow2 fx-distortion2)

(clrfx! :bow2)

(sta)

(stp :bow2)

;;;;;
;;;;;
;;;;;


(trg :samplDrum trg-sampler
     :in-trg ;["sn3"]
      [["bd1" "sn2"] [r r r ["bd2" [(rep 1 "sn2")]]]]
     ;[(evr 5 "bass23" (partition 1 (sfl (fll 16 [["bd2"] ["sn3"] [r]]))))]
     :in-buf ":in-trg"
     :in-loop [0]
     :in-start-pos [0]
     :in-step [2.0]
     :in-amp [1])

(stp :samplDrum)

(stp :nh)

(lss)

(sta)


(evr 2 (fn [x] 44) (partition 1 [1 2 3 4 5 6 6 7 8 9 9 ]))

(defn rep4 [x] (rep 4 x))


(trg :op overpad_i
     :in-trg  (rep 3 [ "e2"]) ["b2"]
     (rep 7 ["a2" ])
     (rep 2 ["d3"]) ["b2"]
     :in-note  ":in-trg"
     :in-gate-select [1]
     :in-attack [0.001]
     :in-decay  [1.1]
     :in-sustain [1.01]
     :in-release [0.35]
     :in-amp [0.19])

(fx! :op fx-distortion2)

(clrfx! :op)

(stp :op)


(trg :op overpad_i
     :in-trg  (rep 3 [(rep 8 "e2")]) [(rep 8 "b2")]
     (rep 7 [(rep 8 "a2") ])
     (rep 2 [(rep 8 "d3")]) [(rep 16 "b2")]
     :in-note  ":in-trg"
     :in-gate-select [1]
     :in-attack [0.001]
     :in-decay  [1.1]
     :in-sustain [1.01]
     :in-release [0.35]
     :in-amp [1])


(fx! :op fx-distortion2)

(clrfx! :op)

(stp :op)

(println (map find-note-name (chr :e2 :minor)))

;;mary had a little lamb

(trg :op overpad
     :in-trg   ["e3" "d2" "c1"  "d2"]
     ["e3" "e3" "e3" r]
     ["d3" "d3" "d3" r]
     ["e3" "g3" "g3" r]
     :in-note  ":in-trg"
      :in-gate-select [1]
      :in-attack [0.01]
      :in-decay  [0.5]
      :in-sustain [0.5]
      :in-release [0.5 0.5 0.5 0.5]
      [0.5 0.5 0.9 r]
      [0.5 0.5 0.9 r]
      :in-amp [0.5])


(trg :kick2 kick2_i :in-trg  [1 1 1 1]
     [1 1 1 r]
     [1 1 1 r]
     [1 1 1 r]
     :in-amp [1])

(stp :kick2)
(sta)



;;




(sta)

(stp :op)

(trg :kick2 kick2_i :in-trg   (rep 7 [1 1 1 1]) [1 [1 r] 1  [1 1]]
     :in-amp [3])

(fx! :kick2 fx-distortion-tubescreamer)

(clrfx! :kick2)

(do
  (volume! :kick2 0)
  (clrfx! :kick2)

  (trg :kick2 kick2_i :in-trg  [(rep 16 1 )]     :in-amp [3])
  (Thread/sleep 1000)
  (volume! :kick2 1)
  (Thread/sleep 1000)

(trg :kick2 kick2_i :in-trg   (rep 17 [1 1 1 1]) [1 [1 r] 1  [1 1]]
     :in-amp [3])
  )

(trg :tom1
     tom
     :in-trg [(repeat 12 1)] ; [1] [(repeat 8 1)] [1 1 1 1] (repeat 5 [r])
     :in-stick-level (repeat 13 [0.1]) [3.915]
     :in-amp [1])

(alg :tom1 :in-trg 0  example_markov bf2)

(rm-alg :tom1 :in-trg 0)

(stp :tom1)

;hyvää bassoa
(trg :vb
     vintage-bass_i
     :in-trg   ; [(rep 6 "a4")]
                                        ;(vec (repeat 2 (seq ["b4" "b4" "b4" ["e4" ["e4" "d4"]] ])))
                                        ;[["a4" "d4"] "e4" "b4" "b4"]
     [["b4" "e4"] "b4" "b4" ["e4" ["e4" "d4"]] ]
     ;(repeat  3 [r])
     ;["a5"]
     ;["b5"]
     ;["d5"]
     ;["e5" "d3" "b2" "b4"]
     :in-gate-select   (rep 4 [1]) (rep 4 [0])
     :in-amp [1.4]
     :in-note  ":in-trg"
     :in-a [0.001]
     :in-d [0.93]
     :in-s [0.95]
     :in-r [0.35])

(fx! :vb fx-distortion2)
(fx! :vb fx-reverb)
(clrfx! :vb)


(stp :vb)

(lss)

(sta)

(trg :bow
     bowed_i
     :in-trg ["a2" r "b2" ["e2" "d2" r "b2"]]
     (rev [["a2" "a3"] r "b2" ["e2" "d2" r "b2"]])
     ["b2" "b2" "b2" r ["b2" "e2" "d2" "b2"] "b2" "b2" "a3"]
     [(rep 4 "a2")] (repeat 2  [r])
     (vec (repeat 2 (seq ["b2" "b2" "b2" ["e2" ["e2" "d2"]] ])))
     [["a2" "d2"] "e2" "b2" "b2"]
     :in-amp [2]
     :in-note  ":in-trg"
     :in-velocity [1]
     :in-gate-select [1] [1]             ;(rep 3 [1]) [1]
     :in-bow-offset [0.01]
     :in-bow-position  [0.75]
     :in-bow-slope [0.8]
     :in-vib-freq [0.127]
     :in-vib-gain [0.19])



(trg :bow
     bowed_i
     :in-trg
     [(rep 16 "a2")]
     [(rep 6 "a2") (rep 2 "c2")]
     [(rep 8 "b2")]
     [(rep 6 "b2") (rep 2 "d2")]
     [(rep 16 "a3")]
     [(rep 6 "a3") (rep 2 "f2")]
     [(rep 16 "e2")]
     [(rep 6 "e2") (rep 2 "b2")]

     :in-amp [2]  [(rep 6 2) 1 1]
     :in-note  ":in-trg"
     :in-velocity [1]
     :in-gate-select [1] [(rep 6 1) 0 0]           ;(rep 3 [1]) [1]
     :in-bow-offset [0.01]
     :in-bow-position  [0.75]
     :in-bow-slope [0.8]
     :in-vib-freq [0.127]
     :in-vib-gain [0.19])


(fx! :bow fx-feedback)

(fx! :bow fx-echo)

(clrfx! :bow)

(stp :bow)

(lss)

 ["a2"]
    ["a2" "c2"]
    ["b2"]
    ["b2" "d2"]
    ["a3"]
    ["a3" "f2"]
    ["e2"]
    ["e2" "b2"]

(sta)

(trg :bow2
     bowed_i
     :in-trg  (rep 8 (fll 32 [1 0]) ) (rep 4  (fll 16 [1 [0 1]]))
     :in-amp [0.81]
     :in-note (rep 3 ["a2"]) ["b2"]
     (rep 4 ["e2"])
     (rep 4 (fst 8 ["d3" "e2" "d3" "e2" "a2" "b2" "e2"]))

     ;(trigger.algo/chd :i :g)
     :in-gate-select [0]
     :in-bow-offset [0.001]
     :in-bow-position [0.0075]
     :in-bow-slope [0.08]
     :in-vib-freq [0.0127]
     :in-vib-gain [0.019] )



(trg :bow2
     bowed_i
     :in-trg  [1]; (rep 8 (fll 32 [1 0]) ) (rep 4  (fll 16 [1 [0 1]]))
     :in-amp [0.81]
     :in-note  ["a2"]
    ["a2" "c2"]
    ["b2"]
    ["b2" "d2"]
    ["a3"]
    ["a3" "f2"]
    ["e2"]
    ["e2" "b2"]
     ;(trigger.algo/chd :i :g)
     :in-gate-select [0]
     :in-bow-offset [0.001]
     :in-bow-position [0.0075]
     :in-bow-slope [0.08]
     :in-vib-freq [0.0127]
     :in-vib-gain [0.019] )


(fx! :bow2 fx-reverb)

(fx! :bow2 fx-distortion2)

(clrfx! :bow2)

(sta)

(stp :bow2)

(lss)


(trg :ks1
     ks1_i
     :in-trg   ["a3" "a3"] (rep 3  [ "a3"])
     (vec (rep 2 (seq ["b2" "b2" "b2" ["e2" ["e2" "d2"]] ])))
     [["a2" "d2"] "e2" "b2" "b3"]
                                        ;(fst 1 [(map name (map find-note-name (chord-degree :iv :b3 :melodic-minor)))])
     :in-gate-select [0]
     :in-dur [1]
     :in-amp [0.991]
     :in-note ":in-trg"
     :in-decay  [0.9] ;(slw 20 (first [(seq (range 0.2 5 0.01)) (seq (range 5 0.2 -0.01))]))
     :in-coef  (slw 20 (apply concat [(seq (range 0.1 0.9 0.01)) (seq (range 0.9 0.1 -0.01))]))
     )

 (map name (map find-note-name (chord-degree :i :d2 :locrian)))

(stp :ks1)


(trg :lead2
    cs80lead
    :in-trg (rep 3 ["e2"]) ["b2"]
     (rep 7 ["a2"])
     (rep 2 ["d3"]) ["b2"]
                                        ;["a2"]
    ;["a2"] ["a2" "c2"]
    ;["b2"]
    ;["b2"] ["b2" "d2"]
    ;["a3"]
    ;["a3"] ["a3" "f2"]
    ;["e2"]
    ;["e2"] ["e2" "b2"]
    ;[["a2" "a3"] r "b2" ["e2" "d2" r "b2"]]
     ;["b2" "b2" "b2" r ["b2" "e2" "d2" "b2"] "b2" "b2" "a3"]
     ;[(rep 4 "a2")] (repeat 2  [r])
     ;(vec (repeat 2 (seq ["b2" "b2" "b2" ["e2" ["e2" "d2"]] ])))
     ;[["a2" "d2"] "e2" "b2" "b2"]
    :in-freq  ":in-trg"
    :in-vibrate ":in-trg"
    :in-dtune [0.01] ;(slw 1 [(take 8 (cycle [0.2 0.3]))]) [0.1] [0.05] [0.01]
    :in-amp  [0.2])

(stp :lead2)

(trg :mooger
    mooger
    :in-trg    ["a3" "a3"] (rep 3  [ "a3"])
     (vec (rep 2 (seq ["b2" "b2" "b2" ["e2" ["e2" "d2"]] ])))
     [["a2" "d2"] "e2" "b2" "b3"]
    :in-amp [1]
    :in-note ":in-trg"
    :in-gate-select [1]
    :in-osc1 [1]
    :in-osc2 [0]
    :in-attack [0.021]
    :in-decay [0.25]
    :in-sustain [0.3]
    :in-release [0.3]
    :in-cutoff   [2400]
    :in-fattack [0.022]
    :in-fdecay [0.29]
    :in-fsustain [0.3]
    :in-frelease [0.4]
    :in-osc2-level [2]
    :in-osc1-level [1])

(stp :mooger)

(fll 4 ["a4" "a5"])

(trg :super supersaw_i :in-freq (evr 16  (vec  (range  (first (mhz (note :e1)))  (first (mhz (note :e8))) 10 ))  (evr 8  (vec  (range  (first (mhz (note :e8)))  (first (mhz (note :e1))) -10 ))   (rep 16 (fll 2 (first (mhz (nts :g2 :g#2))))) ))
     ;(rep 7  (trigger.algo/fll 32  (first (mhz (trigger.algo/nts :g2 :g#2))) ))  [(range  (first (mhz :e3))  (first (mhz :e1)) -0.5 )]  (rep 7  (trigger.algo/fll 8   (first (mhz (trigger.algo/nts :g2 :g#2)))))  [(range  (first (mhz :e1))  (first (mhz :e3)) 0.5 )]

     :in-amp [0.55]   (fst 8 (mapv  (fn [x] (sqr x 0.15 0.85 1 0.0)) (range 0 1 0.05))) ; [(sir 100 1.0 0.1 25)]
     )

(stp :super)

(sta)

(lss)

(t/start "./b15.glsl" :width 1920 :height 1080 :cams [0] :videos ["../videos/soviet1.mp4" "../videos/uni_fixed.mp4" "../videos/soviet4.mp4" "../videos/spede_fixed.mp4"])

(t/bufferSection 0 0 16925) ; 16800, 43000

(t/bufferSection 1 0 6460)

(t/bufferSection 2 0 0)

(t/bufferSection 3 0 51000)

(t/bufferSection 4 0 0)

(t/set-video-fixed 0 :fw)

(t/set-video-fixed 1 :fw)

(t/set-video-fixed 2 :fw)

(t/set-video-fixed 3 :fw)

(t/set-video-fixed 4 :fw)


(on-trigger (get-trigger-val-id :bow :in-trg) (fn [val] (t/set-dataArray-item 0 val)(t/set-fixed-buffer-index 1 :inc) ) ::bow1 )


(remove-event-handler ::bow1)

(on-trigger (get-trigger-val-id :ks1 :in-trg) (fn [val] (t/set-dataArray-item 2 val) ) ::ks1 )

(remove-event-handler ::ks1)


(on-trigger (get-trigger-val-id :vb :in-note) (fn [val] (t/set-dataArray-item 1 val) ) ::vb-note )



(remove-event-handler ::vb-note)



(on-trigger (get-trigger-val-id :kick :in-f3) (fn [val] (t/set-dataArray-item 2 val) ) ::kickf3 )

(remove-event-handler ::kickf3)



(on-trigger (get-trigger-val-id :super :in-freq) (fn [val] (t/set-dataArray-item 3 val) (t/set-fixed-buffer-index 0 :ff (int val)) ) ::ks1_decay )

(remove-event-handler ::ks1_decay)


(lss)

(t/toggle-recording "/dev/video2")
