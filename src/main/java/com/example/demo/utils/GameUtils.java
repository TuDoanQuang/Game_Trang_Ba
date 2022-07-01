package com.example.demo.utils;

import com.example.demo.enums.PointName;
import com.example.demo.form.GameForm;

public class GameUtils {

	public static boolean isPointLocked(GameForm gameForm, String point) {
		if (PointName.a1.name().equalsIgnoreCase(point) 
				|| PointName.a2.name().equalsIgnoreCase(point)
				|| PointName.a3.name().equalsIgnoreCase(point)) {
			if (StringUtils.isAllEqual(gameForm.getA1(), gameForm.getA2(), gameForm.getA3())) {
				return true;
			}
		}
		
		if (PointName.a3.name().equalsIgnoreCase(point) 
				|| PointName.a4.name().equalsIgnoreCase(point)
				|| PointName.a5.name().equalsIgnoreCase(point)) {
			if (StringUtils.isAllEqual(gameForm.getA3(), gameForm.getA4(), gameForm.getA5())) {
				return true;
			}
		}
		
		if (PointName.a5.name().equalsIgnoreCase(point) 
				|| PointName.a6.name().equalsIgnoreCase(point)
				|| PointName.a7.name().equalsIgnoreCase(point)) {
			if (StringUtils.isAllEqual(gameForm.getA5(), gameForm.getA6(), gameForm.getA7())) {
				return true;
			}
		}
		
		if (PointName.a7.name().equalsIgnoreCase(point) 
				|| PointName.a8.name().equalsIgnoreCase(point)
				|| PointName.a1.name().equalsIgnoreCase(point)) {
			if (StringUtils.isAllEqual(gameForm.getA7(), gameForm.getA8(), gameForm.getA1())) {
				return true;
			}
		}
		//======================================
		
		if (PointName.b1.name().equalsIgnoreCase(point) 
				|| PointName.b2.name().equalsIgnoreCase(point)
				|| PointName.b3.name().equalsIgnoreCase(point)) {
			if (StringUtils.isAllEqual(gameForm.getB1(), gameForm.getB2(), gameForm.getB3())) {
				return true;
			}
		}
		
		if (PointName.b3.name().equalsIgnoreCase(point) 
				|| PointName.b4.name().equalsIgnoreCase(point)
				|| PointName.b5.name().equalsIgnoreCase(point)) {
			if (StringUtils.isAllEqual(gameForm.getB3(), gameForm.getB4(), gameForm.getB5())) {
				return true;
			}
		}
		
		if (PointName.b5.name().equalsIgnoreCase(point) 
				|| PointName.b6.name().equalsIgnoreCase(point)
				|| PointName.b7.name().equalsIgnoreCase(point)) {
			if (StringUtils.isAllEqual(gameForm.getB5(), gameForm.getB6(), gameForm.getB7())) {
				return true;
			}
		}
		
		if (PointName.b7.name().equalsIgnoreCase(point) 
				|| PointName.b8.name().equalsIgnoreCase(point)
				|| PointName.b1.name().equalsIgnoreCase(point)) {
			if (StringUtils.isAllEqual(gameForm.getB7(), gameForm.getB8(), gameForm.getB1())) {
				return true;
			}
		}
		
		if (PointName.c1.name().equalsIgnoreCase(point) 
				|| PointName.c2.name().equalsIgnoreCase(point)
				|| PointName.c3.name().equalsIgnoreCase(point)) {
			if (StringUtils.isAllEqual(gameForm.getC1(), gameForm.getC2(), gameForm.getC3())) {
				return true;
			}
		}
		
		if (PointName.c3.name().equalsIgnoreCase(point) 
				|| PointName.c4.name().equalsIgnoreCase(point)
				|| PointName.c5.name().equalsIgnoreCase(point)) {
			if (StringUtils.isAllEqual(gameForm.getC3(), gameForm.getC4(), gameForm.getC5())) {
				return true;
			}
		}
		
		if (PointName.c5.name().equalsIgnoreCase(point) 
				|| PointName.c6.name().equalsIgnoreCase(point)
				|| PointName.c7.name().equalsIgnoreCase(point)) {
			if (StringUtils.isAllEqual(gameForm.getC5(), gameForm.getC6(), gameForm.getC7())) {
				return true;
			}
		}
		
		if (PointName.c7.name().equalsIgnoreCase(point) 
				|| PointName.c8.name().equalsIgnoreCase(point)
				|| PointName.c1.name().equalsIgnoreCase(point)) {
			if (StringUtils.isAllEqual(gameForm.getC7(), gameForm.getC8(), gameForm.getC1())) {
				return true;
			}
		}
		
		//======================================others
		
		if (PointName.a2.name().equalsIgnoreCase(point) 
				|| PointName.b2.name().equalsIgnoreCase(point)
				|| PointName.c2.name().equalsIgnoreCase(point)) {
			if (StringUtils.isAllEqual(gameForm.getA2(), gameForm.getB2(), gameForm.getC2())) {
				return true;
			}
		}
		
		if (PointName.a4.name().equalsIgnoreCase(point) 
				|| PointName.b4.name().equalsIgnoreCase(point)
				|| PointName.c4.name().equalsIgnoreCase(point)) {
			if (StringUtils.isAllEqual(gameForm.getA4(), gameForm.getB4(), gameForm.getC4())) {
				return true;
			}
		}
		
		if (PointName.a6.name().equalsIgnoreCase(point) 
				|| PointName.b6.name().equalsIgnoreCase(point)
				|| PointName.c6.name().equalsIgnoreCase(point)) {
			if (StringUtils.isAllEqual(gameForm.getA6(), gameForm.getB6(), gameForm.getC6())) {
				return true;
			}
		}
		if (PointName.a8.name().equalsIgnoreCase(point) 
				|| PointName.b8.name().equalsIgnoreCase(point)
				|| PointName.c8.name().equalsIgnoreCase(point)) {
			if (StringUtils.isAllEqual(gameForm.getA8(), gameForm.getB8(), gameForm.getC8())) {
				return true;
			}
		}
		
		return false;
	}
	
	public static void updatePointSelected(GameForm gameForm, String pointSelection, boolean waitForEat, String valueChange) {
		if (PointName.a1.name().equalsIgnoreCase(pointSelection)) {
			if (gameForm.getA1() == null || gameForm.getA1().isEmpty() || waitForEat) {
				gameForm.setA1(valueChange);
			}
		}
		
		if (PointName.a2.name().equalsIgnoreCase(pointSelection)) {
			if (gameForm.getA2() == null || gameForm.getA2().isEmpty() || waitForEat) {
				gameForm.setA2(valueChange);
			}
		}
		if (PointName.a3.name().equalsIgnoreCase(pointSelection)) {
			if (gameForm.getA3() == null || gameForm.getA3().isEmpty() || waitForEat) {
				gameForm.setA3(valueChange);
			}
		}
		if (PointName.a4.name().equalsIgnoreCase(pointSelection)) {
			if (gameForm.getA4() == null || gameForm.getA4().isEmpty() || waitForEat) {
				gameForm.setA4(valueChange);
			}
		}
		if (PointName.a5.name().equalsIgnoreCase(pointSelection)) {
			if (gameForm.getA5() == null || gameForm.getA5().isEmpty() || waitForEat) {
				gameForm.setA5(valueChange);
			}
		}
		if (PointName.a6.name().equalsIgnoreCase(pointSelection)) {
			if (gameForm.getA6() == null || gameForm.getA6().isEmpty() || waitForEat) {
				gameForm.setA6(valueChange);
			}
		}
		if (PointName.a7.name().equalsIgnoreCase(pointSelection)) {
			if (gameForm.getA7() == null || gameForm.getA7().isEmpty() || waitForEat) {
				gameForm.setA7(valueChange);
			}
		}
		if (PointName.a8.name().equalsIgnoreCase(pointSelection)) {
			if (gameForm.getA8() == null || gameForm.getA8().isEmpty() || waitForEat) {
				gameForm.setA8(valueChange);
			}
		}
		
		//===================================================
		if (PointName.b1.name().equalsIgnoreCase(pointSelection)) {
			if (gameForm.getB1() == null || gameForm.getB1().isEmpty() || waitForEat) {
				gameForm.setB1(valueChange);
			}
		}
		if (PointName.b2.name().equalsIgnoreCase(pointSelection)) {
			if (gameForm.getB2() == null || gameForm.getB2().isEmpty() || waitForEat) {
				gameForm.setB2(valueChange);
			}
		}
		if (PointName.b3.name().equalsIgnoreCase(pointSelection)) {
			if (gameForm.getB3() == null || gameForm.getB3().isEmpty() || waitForEat) {
				gameForm.setB3(valueChange);
			}
		}
		if (PointName.b4.name().equalsIgnoreCase(pointSelection)) {
			if (gameForm.getB4() == null || gameForm.getB4().isEmpty() || waitForEat) {
				gameForm.setB4(valueChange);
			}
		}
		if (PointName.b5.name().equalsIgnoreCase(pointSelection)) {
			if (gameForm.getB5() == null || gameForm.getB5().isEmpty() || waitForEat) {
				gameForm.setB5(valueChange);
			}
		}
		if (PointName.b6.name().equalsIgnoreCase(pointSelection)) {
			if (gameForm.getB6() == null || gameForm.getB6().isEmpty() || waitForEat) {
				gameForm.setB6(valueChange);
			}
		}
		if (PointName.b7.name().equalsIgnoreCase(pointSelection)) {
			if (gameForm.getB7() == null || gameForm.getB7().isEmpty() || waitForEat) {
				gameForm.setB7(valueChange);
			}
		}
		if (PointName.b8.name().equalsIgnoreCase(pointSelection)) {
			if (gameForm.getB8() == null || gameForm.getB8().isEmpty() || waitForEat) {
				gameForm.setB8(valueChange);
			}
		}
		
		//================================================
		if (PointName.c1.name().equalsIgnoreCase(pointSelection)) {
			if (gameForm.getC1() == null || gameForm.getC1().isEmpty() || waitForEat) {
				gameForm.setC1(valueChange);
			}
		}
		if (PointName.c2.name().equalsIgnoreCase(pointSelection)) {
			if (gameForm.getC2() == null || gameForm.getC2().isEmpty() || waitForEat) {
				gameForm.setC2(valueChange);
			}
		}
		if (PointName.c3.name().equalsIgnoreCase(pointSelection)) {
			if (gameForm.getC3() == null || gameForm.getC3().isEmpty() || waitForEat) {
				gameForm.setC3(valueChange);
			}
		}
		if (PointName.c4.name().equalsIgnoreCase(pointSelection)) {
			if (gameForm.getC4() == null || gameForm.getC4().isEmpty() || waitForEat) {
				gameForm.setC4(valueChange);
			}
		}
		if (PointName.c5.name().equalsIgnoreCase(pointSelection)) {
			if (gameForm.getC5() == null || gameForm.getC5().isEmpty() || waitForEat) {
				gameForm.setC5(valueChange);
			}
		}
		if (PointName.c6.name().equalsIgnoreCase(pointSelection)) {
			if (gameForm.getC6() == null || gameForm.getC6().isEmpty() || waitForEat) {
				gameForm.setC6(valueChange);
			}
		}
		if (PointName.c7.name().equalsIgnoreCase(pointSelection)) {
			if (gameForm.getC7() == null || gameForm.getC7().isEmpty() || waitForEat) {
				gameForm.setC7(valueChange);
			}
		}
		if (PointName.c8.name().equalsIgnoreCase(pointSelection)) {
			if (gameForm.getC8() == null || gameForm.getC8().isEmpty() || waitForEat) {
				gameForm.setC8(valueChange);
			}
		}
	}
	
	
}
