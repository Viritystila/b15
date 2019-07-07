(ns b15 (:use [trigger.trigger] [trigger.synths] [trigger.algo] [overtone.core]) (:require [viritystone.tone :as t]) )


(require '[clojure.set :as set])

(defn unique-random-numbers [n]
  (let [a-set (set (take n (repeatedly #(rand-int n))))]
    (concat a-set (set/difference (set (take n (range)))
                                  a-set))))

(defn fast [factor input] (vec (repeat factor (seq input ) )))

(defn slow [factor input] (map vec (partition factor input)))

(def bf2 (atom{}))

(reset! bf2  {
                     [0.125] { 0.125 0.91  0.25 0.06  0.5 0.099   0.0625 0.9 }
                     [0.25] {  0.125 0.92  0.25 0.05  0.5 0.099   0.0625 0.9 }
                     [0.5] {   0.125 0.07   0.25 0.09   0.5 0.09  0.0625 0.9}
                     [0.0625] {  0.125 0.9  0.25 0.04  0.5 0.01  0.0625 0.9}
                     } )

(reset! bf2  {[0.0625] {0.0625 0.5 0.125 0.5}
              [0.125 ] {0.0625 0.5 0.125 0.5}})


(trg :kick2 kick2 :in-trg (repeat 7 [r]) [(repeat 16 1)]
 :in-amp [3]
)


(trg :kick kick :in-trg   (fast 16 [1  [1.2 1.5]])
 (fast 16 [1.2  [1.9 2.5]])
                                        ;(repeat 8 [1 r 1 1])
 :in-amp [0.01]
 :in-f3 [(range 90 200 8)] [(range 200 90 -16)] ; (repeat 6 [90])
)

(stp :nh)

(lss)

(unique-random-numbers 20)

(trg :nh hat1
     :in-trg  (repeat 7 [(unique-random-numbers 8)]) [1 1 1 1 1 1 [1 1] [1 1 1 1]]
     :in-amp [0.1])


(trg :nh2 soft-hat
     :in-trg  [1]
     :in-amp [1])

(alg :nh2 :in-trg 0 example_markov bf2)

(rm-alg :nh2 :in-trg 0)

(stp :nh2)

(trg :hz haziti-clap :in-trg [1] (repeat 7 [1 1])
     :in-freq [20]
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
     :in-trg  [1] ; [1 1 r 1] [(unique-random-numbers 8)] ; (repeat 14 [r]) [1 2 3 [4 5 6 7]] [8 7 6 5 4 3 2 1]
     :in-gate-select  [1]
     :in-amp [0.6]
     :in-note (take 3 (cycle [(repeat 4 [(note :g2)])  (repeat 4 [(note :g#2)])]))
     (repeat 4 [(note :e3)])  (repeat 4 [(note :d3)])
     :in-a [0.001]
     :in-d [0.3]
     :in-s [0.7]
     :in-r [0.8])



(alg :vb :in-trg 0  example_markov bf2)

(rm-alg :vb :in-trg 0)


(stp :vb)

(trg :bow
     bowed
     :in-trg (repeat 2  [r]) (vec (repeat 2 (seq [1 1 1 [1 [1 1]] ]))) [[1 1] 1 1 1]
    (repeat 4 [(repeat 16 1)])
     :in-amp [1.5]
     :in-note  (take 3 (cycle [(repeat 4 [(note :g2)])  (repeat 4 [(note :g#2)])]))
     (repeat 4 [(note :e3)])  (repeat 4 [(note :d3)])
     :in-velocity [1]
     :in-gate-select [1] [1] [1] [1 1]
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
     :in-note (slow 1 (chord :d2 :minor))
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
     ks1
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
    :in-dtune (slow 1 [(take 8 (cycle [0.2 0.3]))]) [0.1] [0.05] [0.01]
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

(trg :super supersaw :in-freq [(range (midi->hz (note :d1))  (midi->hz (note :d2)) 0.5 )]
     [ (midi->hz (note :d2))]
      [(range (midi->hz (note :d2))  (midi->hz (note :d1)) -0.5 )]
     :in-amp [0.5])

(stp :super)

(stpa)

(lss)

(trg :op overpad
     :in-trg [1 [1 1] 1] [1 1 1]
     :in-gate-select [1])

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
