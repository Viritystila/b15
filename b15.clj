(ns b15 (:use [trigger.trigger] [trigger.synths] [trigger.algo] [trigger.speech] [overtone.core]) (:require [viritystone.tone :as t]) )

(require '[trigger.insts :refer :all])

(def bf2 (atom{}))

(add-sample "a" (string-to-buffer "bgbgb dbdbd aaaaa ppfpdps"))

(generate-markov-text "kalevala.txt" 60)


(add-sample "d" (string-to-buffer (generate-markov-text "kalevala.txt" 100)))


(add-sample "e" (string-to-buffer (generate-markov-text "paradiselost.txt" 100)))


(add-sample "f" (string-to-buffer (generate-markov-text "generalrelativity.txt" 100)))


(add-sample "g" (string-to-buffer (generate-markov-text "daq.txt" 100)))


(add-sample "h" (string-to-buffer (generate-markov-text "generalparadisedaq.txt" 200)))


(add-sample "i" (string-to-buffer (generate-markov-text "generalparadiselost.txt" 200)))


(add-sample "j" (string-to-buffer (generate-markov-text "generalparadisedaqx2.txt" 200)))



(trg :sampl trg-sampler_i
     :in-trg  [r]
     :in-buf ["j"]
     :in-loop [1]
     :in-step [2] ; (slw 12 [(sir 32 2.5 0 32)])
     )

(fx! :sampl fx-echo)

(fx! :sampl fx-reverb)

(clrfx! :sampl)


(sir 8 1 0.0 4)

(sta)

(stp :sampl)

(list-samples)

(reset! bf2  {
                     [0.125] { 0.125 0.91  0.25 0.06  0.5 0.099   0.0625 0.9 }
                     [0.25] {  0.125 0.92  0.25 0.05  0.5 0.099   0.0625 0.9 }
                     [0.5] {   0.125 0.07   0.25 0.09   0.5 0.09  0.0625 0.9}
                     [0.0625] {  0.125 0.9  0.25 0.04  0.5 0.01  0.0625 0.9}
                     } )

(reset! bf2  {[0.0625] {0.0625 0.5 0.125 0.5}
              [0.125 ] {0.0625 0.5 0.125 0.5}})

(sta)
;Brekakbeat

(set-pattern-duration (/ 1 0.5625))

(trg :kick2 kick2_i :in-trg (rep 3 (del 1 1 (del 0 3 [1 1 ]))) [[1 1 1 r r r r r] 1]
      (rep 3 (del 1 1 (del 0 3 [1 1 ])))  [[r r r 1] [r 1 1 r r r r r]]
     :in-amp [1])



(trg :kick2 kick2_i :in-trg  [[1 1 1 r r r r r] 1]
       [(rep 16 [r 1] )]
       [[r r r 1] [r 1 1 r r r r r]]
     :in-amp [1])


(trg :kick2 kick4_i :in-trg [1])


(stp :kick2)

(trg :nh hat2_i
     :in-trg  (rep 3 [1 1]) [1 [1 1 r r] 1 [r r 1 1]]
     (rep 3 [1 1]) [ [(rep 16 1)]  [(rep 4 1 )] [(rep 64 1)] 1]
     (rep 3 [1 1]) [1 [1 1 1 1 1 r r r ] [r 1] r]
     (rep 3 [1 1]) [1 [1 1 1 1 1 r r r ] [r [1 1]] r]
     :in-amp [0.2]
     :in-decay  (rep 3 [0.2]) [0.2] ; [[0.1 0.1 0.1 0.1 0.1] r r r [1 1 1 1] r r r r]
     )


(trg :nh hat2_i
     :in-trg  [(rep 8 1)] [(rep 16 1)]  [ [(rep 16 1)]  [(rep 4 1 )] [(rep 64 1)] 1]
     :in-amp [0.2]
     :in-decay  [0.3] [(range 0.1 1 0.1)] [(range 0.1 1 0.1)] ; [[0.1 0.1 0.1 0.1 0.1] r r r [1 1 1 1] r r r r]
     )


(stp :nh)


(defn rep4 [x] (rep 4 x))


(trg :op overpad
     :in-trg  [1] ; (rep 3 (del 2 1 (del 0 1 [1 1 1 1 ]))) [1 1 1 1] ; [[1 1 1 r r r r r] 1]
      ;(rep 4 (del 1 1 (del 0 3 [1 1 ]))) ;[1 1 1 1] ;  [r] [r] [r]
     :in-note  (rep 3 ["e2"]) ["b2"]
     (rep 4 ["f#2"])
      (rep 4 ["d3"])
      :in-gate-select [0]
      :in-attack [0.001]
      :in-decay  [1.1]
      :in-sustain [1.01]
      :in-release [0.35]
      :in-amp [1])

(map find-note-name (chr :e2 :7sus2))

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

(trg :kick2 kick2_i :in-trg   (rep 7 [1 1 1 1]) [1 [1 r] 1  [1 1]]
     :in-amp [3])
  )

(trg :kick kick_i
     :in-trg (rep 2  [1 1 1 1 r r r r 1 1 1 1 r r r r]) [1 r r r r r r r r 1 r r r r r r r 1 r r r r r r 1 r r r r r 1 r r r r 1 r r r 1 r r 1] [(rep 8 1)]

     ;[1 (rep 32 r) 1 (rep 16 r) 1 (rep 8 r) 1 (rep 4 r) 1 (rep 2 r) 1 r 1]

                                        ;(repeat 8 [1 r 1 1])
 :in-amp [0.05]
 :in-f3 [(rep 8 (mhz :g0))] ; (repeat 6 [90])
)

(stp :kick)

(stp :nh)

(sta)

(lss)

(unique-random-numbers 20)

(trg :nh hat2_i
     :in-trg  [(rep 8 1)] [[1 1 1 1 1] 1 1 1 [1 1 1 1] 1 1 1] ; (repeat 7 [(unique-random-numbers 8)])
     :in-amp [0.3]
     :in-decay  [(range 0.01 0.1 0.01)] [[0.1 0.1 0.1 0.1 0.1] r r r [1 1 1 1] r r r r])


(trg :nh2 soft-hat
     :in-trg  [1]
     :in-amp [1])

(alg :nh2 :in-trg 0 example_markov bf2)

(rm-alg :nh2 :in-trg 0)

(stp :nh2)

(trg :hz haziti-clap_i :in-trg [1 1 1 ]
     :in-freq [200]
     :in-amp [0.5])

(lss)

(alg :hz :in-trg 0  example_markov bf2)

(rm-alg :hz :in-trg 0)


(stp :hz)

(stp :nh)

(lss)

(trg :bd dub-kick :in-trg (repeat 7 [1]) [(repeat 32 1)] :in-amp [0.001])

(stp :bd)

(lss)

(trg :tom1
     tom
     :in-trg [1 1 1 1] ;[(repeat 128 1)] ; [1] [(repeat 8 1)] [1 1 1 1] (repeat 5 [r])
     :in-stick-level (repeat 13 [0.1]) [0.915]
     :in-amp [3])

(alg :tom1 :in-trg 0  example_markov bf2)

(rm-alg :tom1 :in-trg 0)

(stp :tom1)

(trg :vb
     vintage-bass_i
     :in-trg   (sfl  (fll 32 [r 1])) ; (repeat 14 [r]) [1 2 3 [4 5 6 7]] [8 7 6 5 4 3 2 1]
     :in-gate-select   [1]
     :in-amp [0.6]
     :in-note  (trigger.algo/evr 8 (sfl (trigger.algo/fll 8 (trigger.algo/chd :iii :d3 :minor))) (seq  (trigger.algo/fll 8 [(trigger.algo/fll 4 (trigger.algo/nts :d2 :a2)) (trigger.algo/fll 16  (trigger.algo/nts :a2 :f2))])) )

                                        ;(evr 8 (fll 32 (nts :g3 :g4)) (apply conj (fll 8 [(fll 4 (nts :g2 :g#2)) (fll 16  (nts :e3 :e#3))])) )
     :in-a [0.001]
     :in-d [0.93]
     :in-s [0.5]
     :in-r [0.5])

(map (fn [x] (find-note-name x)) (chord :d4 :minor))

(alg :vb :in-trg 0  example_markov bf2)

(rm-alg :vb :in-trg 0)

(sta)
(stp :vb)

(lss)


(trg :vb
     vintage-bass
     :in-trg   [(rep 8 1)] ; (repeat 14 [r]) [1 2 3 [4 5 6 7]] [8 7 6 5 4 3 2 1]
     :in-gate-select   [1]
     :in-amp [0.6]
     :in-note  (trigger.algo/chd :i :g2 :melodic-minor 4)

                                        ;(evr 8 (fll 32 (nts :g3 :g4)) (apply conj (fll 8 [(fll 4 (nts :g2 :g#2)) (fll 16  (nts :e3 :e#3))])) )
     :in-a [0.001]
     :in-d [0.93]
     :in-s [0.5]
     :in-r [0.5])


(trg :bow
     bowed_i
     :in-trg [(rep 4 1)] ;(repeat 2  [r]) (vec (repeat 2 (seq [1 1 1 [1 [1 1]] ]))) [[1 1] 1 1 1]
     :in-amp [1.5]
     :in-note   [(repeat 4 "g2")  (repeat 4 "g#2")]
     :in-velocity [1]
     :in-gate-select  (rep 3 [1]) [1]
     :in-bow-offset [0.01]
     :in-bow-position  [1.75]
     :in-bow-slope [0.8]
     :in-vib-freq [0.127]
     :in-vib-gain [0.19])

(fx! :bow fx-freeverb)
(fx! :bow fx-distortion-tubescreamer)

(clrfx! :bow)

(stp :bow)



(trg :bow2
     bowed_i
     :in-trg [(rep 4 1)]
     :in-amp [0.5]
     :in-note  (evr 16  (vec  (range (note :e1)  (note :e8) 10 ))  (evr 8  (vec  (range (note :e8)  (note :e1) -10 ))   (rep 16 (fll 16 (nts :e1 :e2))) ) )

     ;(trigger.algo/chd :i :g)
     :in-gate-select [0]
     :in-bow-offset [0.001]
     :in-bow-position [0.075]
     :in-bow-slope [0.008]
     :in-vib-freq [0.00127]
     :in-vib-gain [0.0019] )

(fx! :bow2 fx-reverb)

(clrfx! :bow2)

(sta)

(stp :bow2)


(trg :bow2
     bowed
     :in-trg [1 1 1 1]
     :in-amp [0.5]
     :in-note (slw 1 (chord :d2 :minor))
     :in-velocity [1]
     :in-gate-select [0]
     :in-bow-offset [0.001]
     :in-bow-position [0.75]
     :in-bow-slope [0.08]
     :in-vib-freq [0.127]
     :in-vib-gain [0.19] )

(slow 1  (chord :d2 :minor) )

(stp :bow2)

(lss)

(trg :ks1
     ks1_i
     :in-trg [(repeat 16 1)] [(repeat 8 1)]  (repeat 2 [1 r 1 r]) (repeat 4 [1 [1 1] [1 1] 1])
     :in-dur [3]
     :in-amp [1]
     :in-note (repeat 2 [(chord-degree :i :d2 :minor)])  [(reverse [(chord-degree :iv :g2 :minor)])]
     :in-decay [0.5]                ;[(range 0.1 2 0.1) (range 2 0.1 -0.1)]
     :in-coef [0.1]; [(range 0.1 0.9 0.1)]
     )


(trg :ks1
     ks1_i
     :in-trg [1 1 1 1] [1 1 1 1] [1 1 1 1]  [1 1 1 1] [1 1 1 [1 1]]
     :in-dur [1]
     :in-amp [1]
     :in-note  [(nts :a#2 :a#2 :a#2 :a#2)] [(nts :c#2 :a#2 :c#2 :f2)] [(nts :a#2 :f2)] [(nts :d2 :d2 :d2 :d2)]   [ (seq (nts :d2 :d2 :d2))   (nts :d2 :d2)] ;(slw 3  (chord-degree :i :d2 :minor))
     :in-decay  [0.9] ;(slw 20 (first [(seq (range 0.2 5 0.01)) (seq (range 5 0.2 -0.01))]))
     :in-coef  (slw 20 (apply concat [(seq (range 0.1 0.9 0.01)) (seq (range 0.9 0.1 -0.01))]))
     )

(println (slw 2 (apply concat [(seq (range 0.1 1 0.01)) (seq (range 1 0.1 -0.01))]) ))

(println   (apply concat [(seq (range 0.1 1 0.01)) (seq (range 1 0.1 -0.01))]))

(println "asas")


(slw3 (chord-degree :i :d2 :minor))

(slw 3 [1 2 3 4])

[(chord-degree :i :d2 :minor)]

(stp :ks1)


(trg :lead2
    cs80lead
    :in-trg [1]
    :in-freq  [(take 2 (cycle [(midi->hz (note :g3)) (midi->hz (note :g3)) ]))]
    :in-vibrate [25]
    :in-dtune (slw 1 [(take 8 (cycle [0.2 0.3]))]) [0.1] [0.05] [0.01]
    :in-amp  [0.4])

(stp :lead2)

(trg :mooger
    mooger
    :in-trg [1 r 1 r  1 r 1 r]
    :in-amp [1]
    :in-note (repeat 4 [(note :d2)]) (repeat 4 [(note :g2)])
    :in-gate-select [1]
    :in-osc1 [0]
    :in-osc2 [0]
    :in-attack [0.021]
    :in-decay [0.95]
    :in-sustain [0.4]
    :in-release [0.03]
    :in-cutoff   [400]
    :in-fattack [0.022]
    :in-fdecay [0.09]
    :in-fsustain [0.09]
    :in-frelease [0.01]
    :in-osc2-level [2]
    :in-osc1-level [1])

(chord :d2 :minor)

(stp :mooger)

(trg :ping ping :in-trg [1])

(stp :ping)

(trg :super supersaw_i :in-freq (evr 16  (vec  (range  (first (mhz (note :e1)))  (first (mhz (note :e8))) 10 ))  (evr 8  (vec  (range  (first (mhz (note :e8)))  (first (mhz (note :e1))) -10 ))   (rep 16 (fll 2 (first (mhz (nts :g2 :g#2))))) ))
     ;(rep 7  (trigger.algo/fll 32  (first (mhz (trigger.algo/nts :g2 :g#2))) ))  [(range  (first (mhz :e3))  (first (mhz :e1)) -0.5 )]  (rep 7  (trigger.algo/fll 8   (first (mhz (trigger.algo/nts :g2 :g#2)))))  [(range  (first (mhz :e1))  (first (mhz :e3)) 0.5 )]

     :in-amp  ;(map (fn [x] (fst x (mapv  (fn [x] (sqr x 0.15 0.85 1 0.0)) (range 0 1 0.1)))) (range 5 15 1))
     (fst 8 (mapv  (fn [x] (sqr x 0.15 0.85 1 0.0)) (range 0 1 0.05))) ; [(sir 100 1.0 0.1 25)]
     )

(odoc squared-shape)

(stp :super)

(sta)

(lss)

(trg :op overpad
     :in-trg [(rep 4 1)]
     :in-note  (fll 128  (nts :g2 :g#3))  (fll 128  (nts :e2 :e#3))
     :in-gate-select [1]
     :in-amp [0.15])

(stp :op)

(stp :kick2)

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
