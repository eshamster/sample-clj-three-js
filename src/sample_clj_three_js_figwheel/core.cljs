(ns sample-clj-three-js-figwheel.core
    (:require cljsjs.three))

(enable-console-print!)

(defonce frame-counter (atom 0))

(defn change-color [mesh new-color]
  (aset mesh "material" "color" (js/THREE.Color. new-color))
  (aset mesh "material" "needsUpdate" true))

(defn update-mesh [mesh]
  (aset mesh "rotation" "x" (+ 0.01 (.-x (.-rotation mesh))))
  (aset mesh "rotation" "y" (+ 0.02 (.-y (.-rotation mesh))))
  (let [color-list [0xff0000 0x00ff00 0xaaaaff]
        interval 120]
    (when (= (mod @frame-counter interval) 0)
      (let [div (/ (mod @frame-counter
                        (* interval (count color-list)))
                   interval)]
        (change-color mesh (nth color-list div))))))

(defn update-camera [camera]
  (let [z (.-z (.-position camera))]
    (when (< z 1000)
      (aset camera "position" "z" (+ 0.1 z)))))

(defn update-others []
  (swap! frame-counter #(+ % 1)))

(defn init []
  (let [scene (js/THREE.Scene.)
        screen-width 500
        screen-height 500
        p-camera (js/THREE.PerspectiveCamera.
                  45 (/ screen-width screen-height) 1 10000)
        box (js/THREE.BoxGeometry. 200 200 200)
        mat (js/THREE.MeshBasicMaterial.
             (js-obj "color" 0xff0000
                     "wireframe" true))
        mesh (js/THREE.Mesh. box mat)
        renderer (js/THREE.WebGLRenderer.)]
    ;; Change the starting position of cube and camera
    (aset p-camera "name" "p-camera")
    (aset p-camera "position" "z" 500)
    (aset mesh "rotation" "x" 45)
    (aset mesh "rotation" "y" 0)
    (.setSize renderer screen-width screen-height)
    ;; Add camera, mesh and box to scene and then that to DOM node.
    (.add scene p-camera)
    (.add scene mesh)
    (.appendChild js/document.body (.-domElement renderer))
    ;; Kick off the animation loop updating
    (defn render []
      (update-others)
      (update-mesh mesh)
      (update-camera p-camera)
      (.render renderer scene p-camera))

    (defn animate []
      (.requestAnimationFrame js/window animate)
      (render))

    (animate)))

(defonce to-init-once
  (atom (init)))

(defn setup []
  (println "do setup"))

(defn teardown []
  (println "do teardown"))

(defn on-js-reload []
  (teardown)
  (setup))
