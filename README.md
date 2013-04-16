# tankz

An unfinished tank game written in ClojureScript targeting HTML5 canvas.

## Current Development Progress

Not Even Close

Warning: Canvas test could cause seizures.

## Usage

``` console
$ lein cljsbuild once
$ lein ring server
```

## Design Notes

### Specs

* Level entirely visible (640x480)
* 32x32 tiles (20x15)
* Array keys for movement
* Mouse for targetting and shooting missiles
* 8 graphics for tank rotations (4 four cardinal and 4 diagonal)

### Data Structures

#### Level (defrecord Level ...)
- Grid of Tiles

#### Tile (defrecord Tile ...)
- Graphic
- Blocks

#### Tank
- An entity

#### Missile
- An entity

### Entity System

Use a entity/component system from another project. Rip it out and make it into
a standalone library.

#### Mile Stone 1
* Moving tanks around the screen (entire level is one screen big).
* Collision detection
* Firing missiles with mouse click

#### Mile Stone 2
* Enemy tanks with AI

#### Mile Stone 3
* Multiplayer using WebSockets and a Clojure game server

#### Misc.
* Multiple game types (co-op, etc)
* Multiple enemy types with different characteristics
  - Behavior (aggressive, protective, etc)
  - Weapons (Extra missiles, longer range, more damage)
* Multiple levels
* Power-ups
* 3D top-down view (using WebGL)
* Multiplayer with WebRTC (no server)

* Multiplayer works like this
  - Level X supports 4 tanks
  - Player A connects and plays level X against 3 bots
  - Player B connects and takes place of 1 of 3 bots
  - Player C connects and takes place of 1 of 2 remain bots
  - Player D connects and takes place of last remaining bot
  - Player E connects and plays a new instance of level X against 3 bots

## License

Copyright © 2013 Mark A. Nicolosi

Distributed under the Eclipse Public License, the same as Clojure.
