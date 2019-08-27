(ns b15
  (:use [trigger.trigger]
        [trigger.synths]
        [trigger.algo]
        [trigger.speech]
        [trigger.samples]
        [trigger.trg_fx] [overtone.core]) (:require [viritystone.tone :as t]))

;(require '[trigger.insts :refer :all])

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


(add-tts-sample "i"   "daq.txt"  200)


(add-tts-sample "j"   "generalparadisedaqx2.txt" 200)


(add-tts-sample "k"  "generalx2paradisedaqx2.txt" 200)


(add-tts-sample "i"   "daq.txt"  200)

(trg :sampl trg-sampler
     :in-trg  [1] [r] [r] [r]            ; [1] [r]
     :in-buf  ["b bd1"] ; ["f"]  ["g"] ["f"] ["bd1"]    ; ["g"] (repeat 4 ["f"]) (repeat 4 ["e"])
     :in-loop [0]
     :in-start-pos [0]                ;[(range 10000 20000 1000)] [0] [0]
     :in-step [2] [1.75]           ;(slw 1 [(sir 32 2.5 1 32)])
     :in-amp [1.0])

(trg! :sampl :e trg-fx-echo :in-delay-time [0.01 0.05] [0.1] [0.05] :in-amp [1])


(trg! :sampl :e1 trg-fx-distortion )

(lss)

()
(stp :sampl)

(sta)

(lss)

(stp :kick2)

(trg :sampl2 trg-sampler_i
     :in-trg
     ;(fst [(rep 8 "e")])
     ;(fst [(rep 8 "g")])
     ;[["g" "f"] [r r r ["g" [(rep 1 "f")]]]]
     ;[ (sfl (fll 16 [["g"] ["f" "e"] [r]]))]
     ;[(rep 8 "f")]
     ;[(rep 8 "e")]

     ["d"] (rep 3 [r]);(rep 3 [(rep 2 "d")])
     ["d1"] (rep 3 [r]); (rep 3 [(rep 16 "d2")])
     ["d2"] (rep 3 [r])
     ["d3"] (rep 3 [r]);(rep 3 (fst 4 ["d1" ["d2" "d3"]]))
     :in-buf ":in-trg"
     :in-loop [0]
     :in-start-pos [1]
     :in-step [2] ;[1.5] [2] [2.5] ;[-2]                    ; (slw 8 [(sir 32 2.5 1 32)])
     :in-amp [1.1])


(trg :sampl3 trg-sampler_i
     :in-trg  [r]
     :in-buf ["f"]; ["g"] (repeat 4 ["f"]) (repeat 4 ["e"])
     :in-loop [1]
     :in-step  [2.0]              ;(map vec (partition 1 (range -4 4 0.5)))
     :in-amp [1])


 (stp :sampl3)

(map vec (partition 1 (range 0.5 10)))

(fx! :sampl fx-echo)

(fx! :sampl2 fx-feedback)

(clrfx! :sampl)

(stp :sampl)

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

(trg :kick2 kick2 :in-trg (rep 3 (del 1 1 (del 0 3 [1 1 ]))) [[1 1 1 r r r r r] 1]
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
;;;;                                        ;General paradise lost
;;;;

(add-tts-sample "k"  "generalx2paradisedaqx2.txt" 200)

(add-tts-sample "k_2"  "generalx2paradisedaqx2.txt" 200)


(trg :sampl trg-sampler
     :in-trg  [r]; [1 1 1 1 1 1 1 1]
     :in-buf ["b k_2"]          ; ["g"] (repeat 4 ["f"]) (repeat 4 ["e"])
     :in-loop [1]
     :in-start-pos [0]; [(range 0 120000 1000)]
     :in-step (rep 7 [2])(slw 1 [(sir 32 2.5 1 32)])
     :in-amp [0.3])

(sir 32 404040 1 32)

(trg :sampl trg-sampler
     :in-trg  [1 1 1 1 1 1 1 ] [1 1 1 1 1 1 1 [(rep 4 1)]]
     :in-buf ["b k"]          ; ["g"] (repeat 4 ["f"]) (repeat 4 ["e"])
     :in-loop [1]
     :in-start-pos [(range 0 404040 5000)]
     :in-step (rep 7 [2]) ;(slw 1 [(sir 32 2.5 1 32)])
     :in-amp [0.73])

(stp :sampl)

(trg! :sampl :keffect trg-fx-echo
      :in-decay-time [1]
      :in-delay-time (slw 8 [(sir 32 0.5 0.5 32)])
      :in-amp [2])

(volume! :sampl 3)

(lss)

(stp :keffect)

(stp :sampl)

(sta)

(trg :nh hat2
     :in-trg  (rep 3 [1 1]) [1 [1 1 r r] 1 [r r 1 1]]
     (rep 3 [1 1]) [ [(rep 16 1)]  [(rep 4 1 )] [(rep 64 1)] 1]
     (rep 3 [1 1]) [1 [1 1 1 1 1 r r r ] [r 1] r]
     (rep 3 [1 1]) [1 [1 1 1 1 1 r r r ] [r [1 1]] r]
     :in-amp [0.2]
     :in-decay  (rep 3 [0.2]) [0.5] ; [[0.1 0.1 0.1 0.1 0.1] r r r [1 1 1 1] r r r r]
     )

(stp :nh)

(trg :nh hat2
     :in-trg  (rep 4 [(rep 8 1)])
     [(rep 16 1)]
     ;[[(rep 16 1)] [(rep 4 1 )] [(rep 64 1)] 1]
     ;[r]
     :in-amp [0.4]
     :in-decay  [0.3] [(range 0.1 1 0.1)] [(range 0.1 2 0.1)] ; [[0.1 0.1 0.1 0.1 0.1] r r r [1 1 1 1] r r r r]q
     )

(trg! :nh :nheff trg-fx-distortion2 :in-amount [0.195])

(volume! :nh 0.5)

(stp :nh)

(set-pattern-duration (/ 1 0.5265))

(set-pattern-delay 0)

(sta)


(trg :vb
     vintage-bass
     :in-trg
     [(rep 16 "n a5")]
     [(rep 16 "n b5")]
     [(rep 16 "n d5")]
     [(rep 2 "n e4")  (rep 2 "n c#3")  (rep 2 "n b2")  (rep 2 "n b1")]
     [(rep 16  "n b1")]
     [(rep 16 "n d1")]
     [(rep 16 "n a1")]
     (fst 1 ["n c#2" "n e3" "n b3" "n b2"])
     ;[(rep 16  "n e3")]
     ;[(rep 16 "n b3")]
     ;[(rep 16 "n c4")]
     ;(fst 1 ["n c#2" "n e2" "n b3" "n b4"])
     :in-gate-select  (rep 3 [0] ) [1 0]    ;(rep 4 [0])
     :in-amp [0.8]
     :in-note  ":in-trg"
     :in-a [0.001]
     :in-d [0.93]
     :in-s [0.945]
     :in-r [3.85])

(trg! :vb :distro trg-fx-distortion2 :in-amount [0.65])

(trg! :vb :reverb trg-fx-reverb :in-sig-a [0.13])

(stp :distro)

(stp :reverb)

(set-mixer-out-channel :vb 0)

(map (fn [x] (str "n " x)) ["b1" "d3"])

(lss)

(stp :vb)

(println (map find-note-name (chd :i :e2 :melodic-minor 8)))

(volume! :vb 0.136)

(rpl 2 44 4 5 6 7   3 3 5 6  )


(trg :kick2 kick2
     :in-trg  [[r 1] 1 [r  r r [1 r]] 1] [ (rep 4 [r r r r (rep 16 1) r r r ])]
     :in-freq [60]
     :in-amp [1])

(volume! :kick2 1)


(stp :kick2)

(sta)

(pp-node-tree)

(trg :samplDrum trg-sampler
     :in-trg (rpl 2 ["b bd1" "b sn1" "b sn2" ["b bd1" "b bd2"]] (rep 3 ["b bd1" "b sn1" "b sn2" "b bd2"]))
     (rpl 2 ["b bd1" ["b sn1" "b bd2"] "b bd2" ["b bd1" "b bd2"]] (rep 3 ["b bd1" "b sn1" ["b bd2" "b bd2"]  [(rep 4 "b sn2")]]))
     [["b bd2 " r r "b sn2"] "b bd2" ["b bd1" "b sn3"] "b bd1"]
     [["b bd2 " r r "b sn2"] "b bd2" ["b bd1" "b sn3"] [(rep 2 "b bd2")]]
     [(acc 2 (rep 16 ["b bd2 " "b sn2"])) "b bd2" ["b bd1" "b sn3"] "b bd1"]
     [ (fst 2 ["b bd2 " "b sn2"]) (fst 8 ["b bd2 " "b sn2"]) ( rep 16 ["b bd2 " "b sn2"])  (fst 8 ["b bd2 " "b sn2"])]
     ;[[(rep 32 "b bd2 " "b sn2")] [(rep 16 "b sn3 " "b sn2")]]
     :in-buf ":in-trg"
     :in-loop [0]
     :in-start-pos [0]
     :in-step [2.0]
     :in-amp [0.7])



(trg :samplDrum trg-sampler
     :in-trg (rpl 2 ["b bd1" "b sn1" "b sn2" ["b bd1" "b bd2"]] (rep 3 ["b bd1" "b sn1" "b sn2" "b bd2"]))
     ;[(acc 2 (rep 16 ["b bd2 " "b sn2"])) "b bd2" ["b bd1" "b sn3"] "b bd1"]
     [ (acc (fst 8 ["b sn3 " "b sn2"])) (fst 8 ["b sn3 " "b sn2"])  ( rep 5 r)  (dcl (fst 8 ["b sn3 " "b sn2"])) ]

      ;[ (fst 8 ["b bd2 " "b sn2"]) (fst 16 ["b bd2 " "b sn2"]) ( rep 16 ["b bd2 " "b sn2"])  (fst 8 ["b bd2 " "b sn2"])]
     ;[[(rep 32 "b bd2 " "b sn2")] [(rep 16 "b sn3 " "b sn2")]]
     :in-buf ":in-trg"
     :in-loop [0]
     :in-start-pos [0]
     :in-step [2.0]
     :in-amp [0.6])


(set-mixer-out-channel :samplDrum 0)

(stp :samplDrum)

(def pbSd (map buffer-id  (get-buffer-ids :samplDrum :pattern-value-buf)))


(def pbSm (map buffer-id  (get-buffer-ids :vb :pattern-value-buf)))

(rep 3 [1 2 3] [5 4 3])

(println pbSd)

(println pbSm)

(sta)

(lss)
;;;;
;;;;
;;;;

;;;;
;;;; ;;;;;Sample exists
;;;;
(add-sample "d" (string-to-buffer (generate-markov-text "kalevala.txt" 10)))


(add-sample "d1" (string-to-buffer (generate-markov-text "kalevala.txt" 10)))


(add-sample "d2" (string-to-buffer (generate-markov-text "kalevala.txt" 10)))


(add-sample "d3" (string-to-buffer (generate-markov-text "kalevala.txt" 10)))



(trg :sampl2 trg-sampler
     :in-trg
     ;(fst [(rep 8 "e")])
     ;(fst [(rep 8 "g")])
     ;[["g" "f"] [r r r ["g" [(rep 1 "f")]]]]
     ;[ (sfl (fll 16 [["g"] ["f" "e"] [r]]))]
     ;[(rep 8 "f")]
     ;[(rep 8 "e")]

     ["b d"] (rep 3 [r])(rep 3 [(rep 2 "b d")])
     ["b d1"] (rep 3 [r]); (rep 3 [(rep 16 "d2")])
     ["b d2"] (rep 3 [r])
     ["b d3"] (rep 3 [r]);(rep 3 (fst 4 ["d1" ["d2" "d3"]]))
     :in-buf ":in-trg"
     :in-loop [0]
     :in-start-pos [0]
     :in-step [2] [1.5] [2] [2.5] ;[-2]                    ; (slw 8 [(sir 32 2.5 1 32)])
     :in-amp [0.73])


(stp :sampl2)

(sta)

(trg :op overpad
     :in-trg  (rep 3 [ "n e2"]) ["n b2"]
     (rep 7 ["n a2" ])
     (rep 2 ["n d3"]) ["n b2"]
     :in-note  ":in-trg"
     :in-gate-select [1]
     :in-attack [0.001]
     :in-decay  [1.1]
     :in-sustain [1.01]
     :in-release [0.35]
     :in-amp [0.9])

(trg! :op :opdsitro trg-fx-distortion2)

(stp :op)


(sta)

(trg :bow2
     bowed
     :in-trg  [1]; (rep 8 (fll 32 [1 0]) ) (rep 4  (fll 16 [1 [0 1]]))
     :in-amp [0.81]
     :in-note  ["n a2"]
    ["n a2" "n c2"]
    ["n b2"]
    ["n b2" "n d2"]
    ["n a3"]
    ["n a3" "n f2"]
    ["n e2"]
    ["n e2" "n b2"]
     ;(trigger.algo/chd :i :g)
     :in-gate-select [0]
     :in-bow-offset [0.001]
     :in-bow-position [0.0075]
     :in-bow-slope [0.08]
     :in-vib-freq [0.0127]
     :in-vib-gain [0.019] )


(sta)

(stp :bow2)

;;;;;
;;;;;
;;;;;


;;;
;;;uah
;;;

(trg :samplDrum trg-sampler
     :in-trg; ["sn3"  "bd1"] (rep 3 [r])
    (rep 3 [["b bd1" "b sn2"]  [r ["b bd2" "b sn2"]]])
    [(evr 3 "b bass23" (partition 1 (sfl (fll 8 [["b bd2"] ["b sn3"] [r]]))))]

    (rep 3 [["b bd1" "b sn2"]  [r ["b bd2" ["b sn2" "b sn2"]]]])
    [(evr 3 "b bass23" (partition 1 (sfl (fll 8 [["b bd2"] ["b sn3"] [r]]))))]


    (rep 3 [["b bd1" "b sn2"] [r ["b bd2" [(rep 1 "b sn2")]]]])
    [(evr 5 "b bass23" (partition 1 (sfl (fll 16 [["b bd2"] ["b sn2"] [r]]))))]

    (rep 3 [["b bd1" "b sn2"] [r ["b bd2" ["b bd2" "b sn2"]]]])
    [(evr 5 "b bass24" (partition 1 (sfl (fll 16 [["b bd4"] ["b sn3"] [r]]))))]

    (rep 3 [["b bd1" "b sn2"] [r ["b bd2" [(rep 1 "b sn2")]]]])
     [(evr 2 "b bass24" (partition 1  (fll 16 [["b bd2"] ["b sn3" "b sn2"] [r]])))]


    (rep 2 [["b bd1" "b sn2"] [r ["b bd2" [(rep 1 "b sn2")]]]])
    [(evr 1 "b bass23" (partition 1 (sfl (fll 8 [["b bd2"] ["b sn2" "b sn3"] [r]]))))];
    [(evr 1 "b bass24" (partition 1 (sfl (fll 16 [["b bd2"] ["b sn3"] [r]]))))];

    (rep 3 [["b bd1" "b sn2"] [r ["b bd2" [(rep 1 "b sn2")]]]])
    [(evr 4 "b bass23" (partition 1 (sfl (fll 8 [["b bd2"] ["b sn2"] [r]]))))]

    (rep 3 [["b bd1" "b sn2"] [r ["b bd2" ["b bd2" "b sn2"]]]])
    [(evr 1 "b bass24" (partition 1 (sfl (fll 8 [["b bd4"] ["b sn3"] [r]]))))]

    (rep 3 [["b bd1" "b sn2"] [r ["b bd2" [(rep 1 "b sn2")]]]])
    [(evr 4 "b bass24" (partition 1  (fll 8 [["b bd2"] ["b sn4" "b sn2"] [r]])))]

     :in-buf ":in-trg"
     :in-loop [0]
     :in-start-pos [0]
     :in-step [2.0]
     :in-amp [1])

(stp :samplDrum)

(trg! :samplDrum :sddist trg-fx-distortion)


(lss)

(sta)



(trg :op overpad
     :in-trg
     (rep 2 [(rep 8 "n e2")]) [(rep 8 "n bb2")]
     ["n g2" "n d3" "n f3" "n d3" "n e2" "n bb2" ["n g2" "n f3" "n g2"] "n f3"]


     (rep 2 [(rep 8 "n e2")]) [(rep 8 "nb2")]
     [["n g2" "n d3" "n f3" ]  "n e2" ["n bb2" "n f3" "n e2"] "n f3"]
                                        ;(rep 8 [(rep 8 "d2") ])
     ;(rep 2 [(rep 8 "d3")]) [(rep 8 "b2")] ["a2" "a2" "a2" "a2" "e2" "e2" "e2" ["b2" "e2"]]

     ;(rep 3 [(rep 8 "e2")]) [(rep 8 "e2")]
     ;(rep 8 [(rep 8 "d2") ])
     ;(rep 2 [(rep 8 "d3")]) [(rep 8 "b2")] ["a2" "a2" "a2" "a2" "e2" "e2" "e2" "e2"]

     ;(rep 3 [(rep 8 "e2")]) [(rep 8 "e2")]
     ;(rep 8 [(rep 8 "d2") ])
     ;(rep 2 [(rep 8 "d3")]) [(rep 8 "b2")] ["a2" "a2" "a2" "a2" "e2" "e2" "e2" "e2"]

     ;(rep 3 [(rep 8 "e2")]) [(rep 8 "e2")]
     ;(rep 8 [(rep 8 "d2") ])
     ;(rep 2 [(rep 8 "d3")]) [(rep 8 "b3")] ["a2" "a2" "a2" "a2" "e2" "e2" "e2" "e2"]


     ;(rep 3 [(rep 8 "e3")]) [(rep 8 "e3")]
     ;(rep 8 [(rep 8 "d3") ])
     ;(rep 2 [(rep 8 "d4")]) [(rep 8 "b3")] ["a3" "a3" "a3" "a3" "e3" "e3" "e3" ["b3" "e3"]]

     :in-note  ":in-trg"
     :in-gate-select [1]                 ;(rep 16 [1]) (rep 16 [0])
     :in-attack [0.0001]
     :in-decay  [1.1]
     :in-sustain [1.01]
     :in-release [2.75]
     :in-amp [1])


(trg! :op :opf trg-fx-freeverb :in-room-size [4])


(println (map find-note-name (chr :e2 :minor)))

(println (map find-note-name (chd :i :e2 :locrian 8)))

;(fx! :op fx-distortion2)

;(fx! :op fx-feedback)

;(fx! :op fx-echo)

(clrfx! :op)

(stp :op)

(sta)

;;;;;
;;;;;
;;;;;


;;;;
;;;; DAQ
;;;;



(trg :sampl trg-sampler
     :in-trg  [r]
     :in-buf ["i"]; ["g"] (repeat 4 ["f"]) (repeat 4 ["e"])
     :in-loop [1]
     :in-step [2]                        ;(slw 1 [(sir 32 2.5 1 32)])
     :in-amp [1.0])


(fx! :sampl fx-echo)


(stp :sampl)


(trg :gb
     grunge-bass
     :in-trg   ; [(rep 6 "a4")]
                                        ;(vec (repeat 2 (seq ["b4" "b4" "b4" ["e4" ["e4" "d4"]] ])))
                                        ;[["a4" "d4"] "e4" "b4" "b4"]
     (rep 8 [(rep 16 "n b2")])
     (rep 8 [(rep 16 "n d3")])
     (rep 8 [(rep 16 "n e2")])
     (rep 8 [(rep 16 "n a2")])
     ;(repeat  3 [r])
     ;["a5"]
     ;["b5"]
     ;["d5"]
     ;["e5" "d3" "b2" "b4"]
     :in-gate-select  [1] ; (rep 4 [1]) (rep 4 [0])
     :in-amp [0.5]
     :in-note  ":in-trg"
     :in-a [0.0001]
     :in-d [0.93]
     :in-s [0.95]
     :in-r [0.25]; (slw 32 [(range 0.1 1 0.01)])
     )


(trg! :gb :gdist trg-fx-distortion2 :in-amount [0.95])

(stp :gb)

(trg! :gb :greverb trg-fx-reverb :in-sig-a [0.033])

(volume! :gb 1)

(sta)


(trg :mooger
    mooger
    :in-trg    (rep 2 (fst 4 ["n b3" "n d4" "n e3" "n a3"]))
    (rep 2 (fst 4 ["n a3" "n b3" "n d4" "n e3"]))
     (rep 2 (fst 4 ["n e3" "n a3" "n d4" "n b3"]))
     (rep 2 (fst 4 ["n b3" "n e3" "n a4" "n d4"]))
    ; (rep 8 [(rep 16 "d4")])
    ; (rep 8 [(rep 16 "e3")])
    ; (rep 8 [(rep 16 "a3")])
    :in-amp [1.025]
    :in-note ":in-trg"
    :in-gate-select [1]
    :in-osc1 [0]
    :in-osc2 [2]
    :in-attack [0.021]
    :in-decay [0.25]
    :in-sustain [0.3]
    :in-release [0.3]
    :in-cutoff   [1400]
    :in-fattack [0.022]
    :in-fdecay [0.29]
    :in-fsustain [0.3]
    :in-frelease [0.4]
    :in-osc2-level [2]
    :in-osc1-level [2])




;;;;;
;;;;;
;;;;;




(evr 2 (fn [x] 44) (partition 1 [1 2 3 4 5 6 6 7 8 9 9 ]))

(defn rep4 [x] (rep 4 x))


(trg :op overpad
     :in-trg  (rep 3 [ "n e2"]) ["n b2"]
     (rep 7 ["n a2" ])
     (rep 2 ["n d3"]) ["n b2"]
     :in-note  ":in-trg"
     :in-gate-select [1]
     :in-attack [0.001]
     :in-decay  [1.1]
     :in-sustain [1.01]
     :in-release [0.35]
     :in-amp [0.19])

(clrfx! :op)

(stp :op)


(trg :op overpad_i
     :in-trg
 (rep 7 [(rep 16 "e2")]) [(rep 16 "b3")]
     ;(rep 8 [(rep 8 "d2") ])
     ;(rep 2 [(rep 8 "d3")]) [(rep 8 "b2")] ["a2" "a2" "a2" "a2" "e2" "e2" "e2" ["b2" "e2"]]

     ;(rep 3 [(rep 8 "e2")]) [(rep 8 "e2")]
     ;(rep 8 [(rep 8 "d2") ])
     ;(rep 2 [(rep 8 "d3")]) [(rep 8 "b2")] ["a2" "a2" "a2" "a2" "e2" "e2" "e2" "e2"]

     ;(rep 3 [(rep 8 "e2")]) [(rep 8 "e2")]
     ;(rep 8 [(rep 8 "d2") ])
     ;(rep 2 [(rep 8 "d3")]) [(rep 8 "b2")] ["a2" "a2" "a2" "a2" "e2" "e2" "e2" "e2"]

     ;(rep 3 [(rep 8 "e2")]) [(rep 8 "e2")]
     ;(rep 8 [(rep 8 "d2") ])
     ;(rep 2 [(rep 8 "d3")]) [(rep 8 "b3")] ["a2" "a2" "a2" "a2" "e2" "e2" "e2" "e2"]


     ;(rep 3 [(rep 8 "e3")]) [(rep 8 "e3")]
     ;(rep 8 [(rep 8 "d3") ])
     ;(rep 2 [(rep 8 "d4")]) [(rep 8 "b3")] ["a3" "a3" "a3" "a3" "e3" "e3" "e3" ["b3" "e3"]]

     :in-note  ":in-trg"
     :in-gate-select [1]                 ;(rep 16 [1]) (rep 16 [0])
     :in-attack [0.0001]
     :in-decay  [1.1]
     :in-sustain [1.01]
     :in-release [0.75]
     :in-amp [1])


(fx! :op fx-distortion2)

(fx! :op fx-feedback)

(fx! :op fx-echo)

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


(lss)

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


(trg :vb
     vintage-bass_i
     :in-trg   ; [(rep 6 "a4")]
                                        ;(vec (repeat 2 (seq ["b4" "b4" "b4" ["e4" ["e4" "d4"]] ])))
                                        ;[["a4" "d4"] "e4" "b4" "b4"]
     [(rep 16 "b3")]
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


(trg :gb
     grunge-bass_i
     :in-trg   ; [(rep 6 "a4")]
                                        ;(vec (repeat 2 (seq ["b4" "b4" "b4" ["e4" ["e4" "d4"]] ])))
                                        ;[["a4" "d4"] "e4" "b4" "b4"]
     (rep 8 [(rep 16 "b2")])
     (rep 8 [(rep 16 "d3")])
     (rep 8 [(rep 16 "e2")])
     (rep 8 [(rep 16 "a2")])
     ;(repeat  3 [r])
     ;["a5"]
     ;["b5"]
     ;["d5"]
     ;["e5" "d3" "b2" "b4"]
     :in-gate-select  [1] ; (rep 4 [1]) (rep 4 [0])
     :in-amp [0.05]
     :in-note  ":in-trg"
     :in-a [0.0001]
     :in-d [0.93]
     :in-s [0.95]
     :in-r [0.25]; (slw 32 [(range 0.1 1 0.01)])
     )

(fx! :gb fx-distortion2)


(clrfx! :gb)

(evr 2 [233] (partition 1 [1 2 33 4 5]))




(stp :gb)

(lss)

(stp :mooger)

(sta)

(trg :bow
     bowed_i
     :in-trg
     ["b2" r "a2" ["d2" "d2" r "b2"]]
     (rev [["a2" "a3"] r "b2" ["e2" "d2" r "b2"]])
      [["a2" "a3"] r "b2" ["e2" "d2" r "b2"]]
     (rep 1 [r])
     ;["b2" r "a2" ["d2" "d2" r "b2"]]
     ;(rev [["a2" "a3"] r "b2" ["e2" "d2" r "b2"]])
     ;(rep 2 [r])

     ;["d3" r "a3" ["d3" "d3" r "b3"]]
     ;(rev [["a3" "a4"] r "b3" ["e3" "d3" r "b2"]])
     ;(rep 2 [r])
     ;["d3" r "a3" ["d3" "d3" r "b2"]]
     ;(rev [["a3" "a4"] r "b3" ["e2" "d3" r "b3"]])
     ;(rep 2 [r])

     ;["e2" r "e2" ["e2" "e2" r "e2"]]
     ;(rev [["e2" "e3"] r "e2" ["e2" "e2" r "e2"]])
     ;(rep 2 [r])
     ;["e3" r "e2" ["e2" "e2" r "e2"]]
     ;(rev [["e2" "e3"] r "e2" ["e2" "e2" r "e2"]])
     ;(rep 2 [r])

     ;["a2" r "a2" ["d2" "d2" r "b2"]]
     ;(rev [["a2" "a3"] r "b2" ["e2" "d2" r "a2"]])
     ;(rep 2 [r])
     ;["a2" r "a2" ["d2" "d2" r "b2"]]
     ;(rev [["a2" "a3"] r "b2" ["e2" "d2" r "a2"]])
     ;(rep 2 [r])
     :in-amp [2]
     :in-note  ":in-trg"
     :in-velocity [3]
     :in-gate-select [1]             ;(rep 3 [1]) [1]
     :in-bow-offset [0.05]
     :in-bow-position  [0.75]
     :in-bow-slope [0.8]
     :in-vib-freq [0.00127]
     :in-vib-gain [0.019])

(stp :bow)


(trg :bow
     bowed
     :in-trg
     [(rep 16 "n a2")]
     [(rep 6 "n a2") (rep 2 "n c2")]
     [(rep 8 "n b2")]
     [(rep 6 "n b2") (rep 2 "n d2")]
     [(rep 16 "n a3")]
     [(rep 6 "n a3") (rep 2 "n f2")]
     [(rep 16 "n e2")]
     [(rep 6 "n e2") (rep 2 "n b2")]

     :in-amp [2]  [(rep 6 2) 1 1]
     :in-note  ":in-trg"
     :in-velocity [1]
     :in-gate-select [1] [(rep 6 1) 0 0]           ;(rep 3 [1]) [1]
     :in-bow-offset [0.01]
     :in-bow-position  [0.75]
     :in-bow-slope [0.8]
     :in-vib-freq [0.127]
     :in-vib-gain [0.19])


(fx! :bow fx-distortion2)

(fx! :bow fx-echo)

(clrfx! :bow)

(stp :bow)

(sta)

(lss)

(sta)


(fx! :bow2 fx-reverb)

(fx! :bow2 fx-distortion2)

(clrfx! :bow2)

(sta)

(stp :bow2)

(lss)


(trg :ks1
     ks1
     :in-trg   ["n a3" "n a3"] (rep 3  [ "n a3"])
     (vec (rep 2 (seq ["n b2" "n b2" "n b2" ["n e2" ["n e2" "n d2"]] ])))
     [["n a2" "n d2"] "n e2" "n b2" "n b3"]
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
    :in-trg (rep 3 ["n e2"]) ["n b2"]
     (rep 7 ["n a2"])
     (rep 2 ["n d3"]) ["n b2"]
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
    :in-amp  [0.72])

(stp :lead2)

(trg :mooger
    mooger_i
    :in-trg    (rep 2 (fst 4 ["b3" "d4" "e3" "a3"]))
    (rep 2 (fst 4 ["a3" "b3" "d4" "e3"]))
     (rep 2 (fst 4 ["e3" "a3" "d4" "b3"]))
     (rep 2 (fst 4 ["b3" "e3" "a4" "d4"]))
    ; (rep 8 [(rep 16 "d4")])
    ; (rep 8 [(rep 16 "e3")])
    ; (rep 8 [(rep 16 "a3")])
    :in-amp [1.025]
    :in-note ":in-trg"
    :in-gate-select [1]
    :in-osc1 [0] [0] [1] [1] [2] [2]
    :in-osc2 [2]
    :in-attack [0.021]
    :in-decay [0.25]
    :in-sustain [0.3]
    :in-release [0.3]
    :in-cutoff   [3400]
    :in-fattack [0.022]
    :in-fdecay [0.29]
    :in-fsustain [0.3]
    :in-frelease [0.4]
    :in-osc2-level [2]
    :in-osc1-level [2])

(fx! :mooger fx-echo)

(clrfx! :mooger)

(stp :mooger)

(lss)

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



(defn vt-text []
(str "
           ██▒          ██▒    ▒                     ▓    ███ ░███
                              ██                    ░█          ██
 ███   █▒ ░██▒ ░██░███▓░██▒ ░███░░ ███  ░█░ ██ ░██ ░██░░ ░███   ██  ░█▒ ▓██
  ███ ▒░   ██▒  ███     ██▒  ███    ███ █   █████░  ██    ███   ██    ░████
   ████    ██▒  ███     ██▒  ███ █   ███    █   ██  ██ ░▒ ███   ██  ██▒ ░██
    ██    ▒▒▒▒░▒▒▒▒▒   ▒▒▒▒░  ▓█▒     █     ░░██░    ██░ ░▒▒▒▒░▒▒▒▒  ██▓ ░█▒
                                  ████
"))

(println (vt-text))

(defn vt-logo
  []
  (str "
███████ ██░▒██░▒▒▒▒░▓▓▓██████████░▓▓███
██████▒░██▒░░░ ░░▒░▒░░  ░░░▒████▒ █▒░▓▒
████▓██▒▓▒▓░░░░░░   ▒░ ░  ░░  ░▓█░░▒▓██
██████▒░▒ ░  ░░░░   ░░░    ░▒  ░ ░█████
█████▓░▒█░  ░░    ░░ ░░ ▒░  ▒ ░░ ░ ████
▓████▓ ▒░▒░░ ▓▒▓▒░   ░▒░▒░░░ ░░  ░  ███
░████▒░   ░  ▒▓▓▓▒ ░░  ░▒▒▓▓▓░░░░░  ▓██
▓▓█░██▒    ░ ░░▓▒▓   ░░ ▒▒▒▒▓░ ▒    ███
▓██████░▒░▒░ ▒▒▒▓░  ░░▒░ ▓▓▓▒░▒     ███
██████░▒▓ ░░▒░ ░░░░░░        ░      ███
████▓█░▓▓░ ▒░█▒▒ ░░  ░░ ░  ░   ░░  ░███
██████▓▒░░░▒░  ░░░░░░  ▓   ░░   ░   █▓░
███████▒▒▒  ░░▒░░  ░░ ░   ░    ░   ▓███
██████▒▒   ░ ░░░ ░ ░ ░ ░░ ░░░  ░   ▒▒▓▒
░███░▓█▒░░ ░▒▒▒  ░ ░░░░░▒▓▒▒▒▒▒ ░░ ▒░▒
█▓███▓░▒░░▒██▓▒▓▓▓▓▓▓▓▓▓▓▓▓▒▒▓░ ░ ░████
█▓▓█▓█▒░ ▒▓▓▓▓▓█▓▓▓█▓▓▓▓▓▓▓▓▓▓▒ ░ ░████
█▓██▓▓▓▒ ▒  ░▒░▒ ░░▒ ▒▒░░░░   ░░ ░ ████
██▓█░▓▓ ░ ░░░  ░░░░▒░░░ ▒░ ░░ ░▒ ▒ ████ "))


(println (vt-logo))



(defn vt-logo2
  []
  (str "        ▒   ▒ ▒  ▒▒▒▓▒▒▓▒▒▒▒▒           ▒▓
       ▓ ▒ ▒▒▒▓▒▒▓▓▓▓▓▓▓▒▒▒▒▓▒▒▒▒▒     ▒ ▒ ▓ ▒▓▒
           ▓▒▓▓▓▓▓▓▓▓▒▓▓▒▒▒▒▒▓▒▓▓▓▒▓▒▒  ▒▓
       ▒▒▒▓▒▓▓▓▓▒▓▓▓▓▓▓▓▓▓▓▓▓▓▒▓▓▒▒▓▓▒▓▒
      ▒▒▒▒▒▒▓▓▓▒▓▓▓▒▒▒▒▓▒▓▓▒▒▓▒▓▒▓▓▓▒▒▒▒ ▒
       ▒▒ ▒▒▓▒▓▒▓▒▓▓▓▓▓▓▒▒▓▒▓▒▒▓▒▒▒▒▓ ▒▓ ▓▒
      ▒▒ ▓▓▒▓▓▓██████▓▓▓ ▓▒▓▓█▓▓███▒▓▒▒▒▒▒▒▒
      ▒▒▓▓▓▓▓ ▓█▓████▓▓▓▓▓▓▒▓██████▓▒▒▒▒▒▒▓▒
     ▒  ▓▓▒▓▓▓▓████▓█▓▓▒▓▓▒▓▒██████▓▓▓▒▒▓▓▓▒
        ▒ ▒▓▒▓▓▓██▒██▓▓▓▓▒▓▒▓▓▓█████▓▒▓▓▓▒▒▒
       ▒▒▒▒▓▒▓▓█████▓▓▓▓▒▒▒▓▒▒▓▓▓▓█▓▓▓▓▓▓▓▓▒
         ▒▒▒▓ ▒▒▓▒▓▓▒▓▓▓▒▓▒▓▒▓▒▒▒▓▓▒▓▓▓▓▓▓▓▒
      ▒▒   ▓▒▒▒ ▒▒▓▓▒▓▓▓▓▓▓▓▒▒▓▓▓▒▒▓▓▒▒▓▓▓▓
         ▒▒▒▒▒▒▒▒▓▓▓▓▓▓▓▓▓▓▓  ▓▓▒▒▓▓▒▓▒▒▓▓▒▒▒▒▓
         ▒▒▒▒▓▓▓▓▓▓▓▓▓▒▓▒▓▓▓▒▒▓▓▓▒▒▒▓▒▓▒▓▓▒
       ▒▒ ▒▓▓▓▓▓▓▓▓▓▓▓▓▒▒▓▓▒▓▓▓▓▓▓▓▒▓▓▒▓▓▒▓
       ▒▒▒▒▓▓▓▓▓▓▓▓▒▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▒▓▓▓▓▓▒▓▒ ▒▒▒
    ▒  ▒▒▓▒▓▓▓▓██▓▒▒▓▓▓▓▓▓▓▓▓███▓██▒▓▓▓▓▓▓▓▒▒█▒▒
       ▓▓▓▒▓█████████████████████████▓▓▓▒▒▓
       ▒▓▓▒▓█████████████████████████▓▓▒▒▓
       ▒▒▒▒▓█████████████████▓▓▓▓▓▓▓▓▓▓▓▒▒
 ▒      ▒▓▒▒▒▓▓▓▓█▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓
 █  ▒  ▒▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓█▓▓▒▓▒▓▓▓▓▓▓▓▓
      ▒ ▒▒▓▓▓▓▓▓▓▓▓▓▓▓▓▓█▓▓▓█▓▒▒▒▓▓▓▓▓▓▓▓▓▒  "))


(println (vt-logo2))



(defn vt-logo3
  []
  (str "          ░▒  ░▒▒▒▒░▓▓▓          ░▓▓
      ▒░  ▒░░░ ░░▒░▒░░  ░░░▒    ▒  ▒░▓▒
    ▓  ▒▓▒▓░░░░░░   ▒░ ░  ░░  ░▓ ░░▒▓
      ▒░▒ ░  ░░░░   ░░░    ░▒  ░ ░
     ▓░▒ ░  ░░    ░░ ░░ ▒░  ▒ ░░ ░
▓    ▓ ▒░▒░░ ▓▒▓▒░   ░▒░▒░░░ ░░  ░
░    ▒░   ░  ▒▓▓▓▒ ░░  ░▒▒▓▓▓░░░░░  ▓
▓▓ ░  ▒    ░ ░░▓▒▓   ░░ ▒▒▒▒▓░ ▒
▓      ░▒░▒░ ▒▒▒▓░  ░░▒░ ▓▓▓▒░▒
      ░▒▓ ░░▒░ ░░░░░░        ░
    ▓ ░▓▓░ ▒░ ▒▒ ░░  ░░ ░  ░   ░░  ░
      ▓▒░░░▒░  ░░░░░░  ▓   ░░   ░    ▓░
       ▒▒▒  ░░▒░░  ░░ ░   ░    ░   ▓
      ▒▒   ░ ░░░ ░ ░ ░ ░░ ░░░  ░   ▒▒▓▒
░   ░▓ ▒░░ ░▒▒▒  ░ ░░░░░▒▓▒▒▒▒▒ ░░ ▒░▒
 ▓   ▓░▒░░▒  ▓▒▓▓▓▓▓▓▓▓▓▓▓▓▒▒▓░ ░ ░
 ▓▓ ▓ ▒░ ▒▓▓▓▓▓ ▓▓▓ ▓▓▓▓▓▓▓▓▓▓▒ ░ ░
 ▓  ▓▓▓▒ ▒  ░▒░▒ ░░▒ ▒▒░░░░   ░░ ░
  ▓ ░▓▓ ░ ░░░  ░░░░▒░░░ ▒░ ░░ ░▒ ▒     "))


(println (vt-logo3))
