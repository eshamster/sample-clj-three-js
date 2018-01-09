(ns sample-clj-three-js-figwheel.core
    (:require cljsjs.three))

(enable-console-print!)

;; define your app data so that it doesn't get over-written on reload

(defonce app-state (atom {:text "Hello world!"}))

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
      (aset mesh "rotation" "y" (+ 0.01 (.-y (.-rotation mesh))))
      (.render renderer scene p-camera))

    (defn animate []
      (.requestAnimationFrame js/window animate)
      (render))

    (animate)))

(init)

(defn setup []
  (println "do setup"))

(defn teardown []
  (println "do teardown"))

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
  (teardown)
  (setup)
)
