(ns b15 (:use [trigger.trigger] [trigger.synths] [trigger.algo] [overtone.core]) (:require [viritystone.tone :as t]) )

(require '[trigger.insts :refer :all])

(def bf2 (atom{}))

(reset! bf2  {
                     [0.125] { 0.125 0.91  0.25 0.06  0.5 0.099   0.0625 0.9 }
                     [0.25] {  0.125 0.92  0.25 0.05  0.5 0.099   0.0625 0.9 }
                     [0.5] {   0.125 0.07   0.25 0.09   0.5 0.09  0.0625 0.9}
                     [0.0625] {  0.125 0.9  0.25 0.04  0.5 0.01  0.0625 0.9}
                     } )

(reset! bf2  {[0.0625] {0.0625 0.5 0.125 0.5}
              [0.125 ] {0.0625 0.5 0.125 0.5}})


(trg :kick2 kick2_i :in-trg (rpl 4 (rtm rand-int 32  32 ) (rep 8 [r])) [(rep 8 1)]
     :in-amp [3])

(fx! :kick2 fx-compressor)

(clrfx! :kick2)

(stp :kick2)

(trg :kick kick
     :in-trg [1 (rep 6 r) 1] [1 1 (rep 6 r) ] [r]
     (rep 1 [1 (rep 24 r) 1 (rep 20 r) 1 (rep 15 r) 1 (rep 10 r) 1 (rep 8 r) 1 (rep 6 r) 1 (rep 4 r) 1 (rep 2 r) 1])

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

(trg :nh hat1
     :in-trg  (repeat 7 [(unique-random-numbers 8)]) [[1 1 1 1 1] 1 1 1 [1 1 1 1] 1 1 1]
     :in-amp [0.03])


(trg :nh2 soft-hat
     :in-trg  [1]
     :in-amp [1])

(alg :nh2 :in-trg 0 example_markov bf2)

(rm-alg :nh2 :in-trg 0)

(stp :nh2)

(trg :hz haziti-clap :in-trg (rep 16 rtm rand-int 32 32)
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
     :in-trg [1] ;[(repeat 128 1)] ; [1] [(repeat 8 1)] [1 1 1 1] (repeat 5 [r])
     :in-stick-level (repeat 13 [0.1]) [0.915]
     :in-amp [1])

(alg :tom1 :in-trg 0  example_markov bf2)

(rm-alg :tom1 :in-trg 0)

(stp :tom1)

(trg :vb
     vintage-bass
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
    (rep 4 rtm rand-int 16 16)
     :in-amp [1.5]
     :in-note  (take 3 (cycle [(repeat 4 (nts :g2))  (repeat 4 (nts :g#2))]))
     (repeat 4 (nts :e3))  (repeat 4 (nts :e4))
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
     bowed
     :in-trg [(rep 4 1)] [(rep 8 1)] [(rep 16 1)]  [(rep 8 1)]
     :in-amp [1.5]
     :in-note  (trigger.algo/chd :i :g1 :melodic-minor 4)
     :in-gate-select [1]
     :in-bow-offset [0.01]
     :in-bow-position [1.75]
     :in-bow-slope [0.08]
     :in-vib-freq [0.127]
     :in-vib-gain [0.19] )


(stp :bow)

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
     :in-trg  [(repeat 16 1)] [(repeat 8 1)] (repeat 2 [1 r 1 r]) (repeat 4 [1 [1 1] [1 1] 1])
     :in-dur [3]
     :in-amp [1]
     :in-note (repeat 2 [(chord-degree :i :d2 :minor)])  [(reverse [(chord-degree :iv :g2 :minor)])]
     :in-decay [(range 0.1 2 0.1) (range 2 0.1 -0.1)]
     :in-coef [(range 0.1 0.9 0.1)]  )


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

(trg :super supersaw :in-freq  (rep 7  (trigger.algo/fll 16  (mhz (trigger.algo/nts :g2 :g#2))))
     [(range  (first (mhz :e3))  (first (mhz :e1)) -0.5 )]
      :in-amp [(sir 100 0.5 0.1 1000)]
      )


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
