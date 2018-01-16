# sample-clj-three-js-figwheel

The sample-clj-three-js-figwheel is my first sample to use ClojureScript especially for trying hot loads. It mainly consists of ...

- [Figwheel](https://github.com/bhauman/lein-figwheel)
- [cljsjs/three](https://github.com/cljsjs/packages/tree/master/three)

## Hot loads demo

***WIP***

## Setup

### Use within console

To get an interactive development environment run:

    lein figwheel

and open your browser at [localhost:3449](http://localhost:3449/).
This will auto compile and send all changes to the browser without the
need to reload. After the compilation process is complete, you will
get a Browser Connected REPL. An easy way to try it is:

    (js/alert "Am I connected?")

and you should see an alert in the browser window.

To clean all compiled files:

    lein clean

To create a production build run:

    lein do clean, cljsbuild once min

And open your browser in `resources/public/index.html`. You will not
get live reloading, nor a REPL. 

### Use within nREPL

If you want to use this within nREPL, please follow [the official instruction of Figwheel](https://github.com/bhauman/lein-figwheel/wiki/Using-the-Figwheel-REPL-within-NRepl). Settins written in it has already been done (they are included in Figwheel template), so you require to execute followings in your REPL.

```clojure
> (use 'figwheel-sidecar.repl-api)
> (start-figwheel!)
> (cljs-repl)
;; Then, you can access to it from your browser. 
```

Such operation is required, for example, to cooporate with [Cider](https://github.com/clojure-emacs/cider) on Emacs.

## License

Copyright © 2018

Distributed under the Eclipse Public License either version 1.0 or (at your option) any later version.
