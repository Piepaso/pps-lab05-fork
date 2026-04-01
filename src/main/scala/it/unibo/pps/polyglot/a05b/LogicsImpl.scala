package it.unibo.pps.polyglot.a05b

import it.unibo.pps.ex.Vector2D
import it.unibo.pps.polyglot.a05b.Logics
import it.unibo.pps.util.Sequences.Sequence
import Sequence.*
import  Math.abs

/** solution and descriptions at https://bitbucket.org/mviroli/oop2019-esami/src/master/a05b/sol2/ */
class LogicsImpl(private val size: Int) extends Logics:

  private val random = scala.util.Random()
  private val cx = random.nextInt(size - 2) + 1
  private val cy = random.nextInt(size - 2) + 1
  private val centre = (cx, cy)
  private var epoch = 0

  override def tick(): Unit = epoch += 1

  override def isOver: Boolean = !centre.toList.forall(d => epoch <= d && epoch < size - d)

  override def hasElement(x: Int, y: Int): Boolean =
    abs(x - cx) <= epoch && abs(y - cy) <= epoch && (x == cx || y == cy || abs(x - cx) == abs(y - cy))
