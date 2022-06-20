.PHONY: clean start test uberjar


clean:
	clojure -T:build clean

start:
	clojure -M -m integrant-play.core

test:
	clojure -X:test

uberjar: clean
	clojure -T:build uberjar
