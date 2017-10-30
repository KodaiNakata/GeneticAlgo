package GeneticAlgo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Objects;

/*
 * 教室と何曜日と何限目を決めるためのクラス
 * @author Nakata
 */
public class Decide_roomAndDayAndPeriod extends Decide_faculty {

	private TimeTable[][] f_PreviousTimeTables = new TimeTable[MAX_DAY][MAX_PERIOD];// 前期の時間割
	private TimeTable[][] f_LatterTimeTables = new TimeTable[MAX_DAY][MAX_PERIOD];// 後期の時間割
	private double f_OldEvaluationValue;// 前の評価値
	private double f_NewEvaluationValue;// 新しい評価値
	private int f_RandomDayOfWeek1;// ランダムの曜日
	private int f_RandomPeriod1;// ランダムの限目
	private int f_RandomGrade1;// ランダムの学年
	private int f_RandomPreviousOrLatter1;// 前期か後期か
	private int f_RandomDayOfWeek2;// ランダムの曜日
	private int f_RandomPeriod2;// ランダムの限目
	private int f_RandomGrade2;// ランダムの学年
	private int f_RandomPreviousOrLatter2;// 前期か後期か
	private TimeTable f_TimeTableDataTmp1;// 時間割のデータの交換1
	private TimeTable f_TimeTableDataTmp2;// 時間割のデータの交換2
	private int f_TmpNumber1;// 交換用の番目1
	private int f_TmpNumber2;// 交換用の番目2
	private ArrayList<TimeTable> f_NewTimeTableData3 = new ArrayList<TimeTable>();// 新規の3次の時間割のデータ

	protected ArrayList<TimeTable> f_TimeTableData1 = new ArrayList<TimeTable>();// 1次の時間割のデータ
	protected ArrayList<TimeTable> f_TimeTableData2 = new ArrayList<TimeTable>();// 2次の時間割のデータ
	protected ArrayList<TimeTable> f_TimeTableData3 = new ArrayList<TimeTable>();// 3次の時間割のデータ

	/*
	 * コンストラクタ
	 */
	public Decide_roomAndDayAndPeriod() {
		super();
	}

	/*
	 * データの表示
	 */
	private void indicateData() {

		for (int number = 0; number < f_TimeTableData1.size(); number++) {
			System.out.print(f_TimeTableData1.get(number).getDayOfWeek()
					+ "曜日、");// 曜日
			System.out.print(f_TimeTableData1.get(number).getPeriod() + "限目、");// 限目
			System.out.print(f_TimeTableData1.get(number).getClassOfGrade()
					.getNumber()
					+ "コマ、");// コマ数
			System.out.print(f_TimeTableData1.get(number).getClassOfGrade()
					.getGrade()
					+ "年、");// 学年
			System.out.print(f_TimeTableData1.get(number).getClassOfGrade()
					.getPreviousOrLatter()
					+ "、");// 前期・後期
			System.out.print(f_TimeTableData1.get(number).getClassOfGrade()
					.getSubject()
					+ "、");// 科目名
			System.out.print(f_TimeTableData1.get(number).getClassOfGrade()
					.getTeachers().getName()
					+ "先生、");// 担当教員
			System.out.print(f_TimeTableData1.get(number).getClassRoom()
					+ "教室、");// 教室
			System.out.println(f_TimeTableData1.get(number).getClassOfGrade()
					.getCourseOrClass()
					+ "クラス");// コース・クラス
		}

		for (int number = 0; number < f_TimeTableData2.size(); number++) {
			System.out.print(f_TimeTableData2.get(number).getDayOfWeek()
					+ "曜日、");// 曜日
			System.out.print(f_TimeTableData2.get(number).getPeriod() + "限目、");// 限目
			System.out.print(f_TimeTableData2.get(number).getClassOfGrade()
					.getNumber()
					+ "コマ、");// コマ数
			System.out.print(f_TimeTableData2.get(number).getClassOfGrade()
					.getGrade()
					+ "年、");// 学年
			System.out.print(f_TimeTableData2.get(number).getClassOfGrade()
					.getPreviousOrLatter()
					+ "、");// 前期・後期
			System.out.print(f_TimeTableData2.get(number).getClassOfGrade()
					.getSubject()
					+ "、");// 科目名
			System.out.print(f_TimeTableData2.get(number).getClassOfGrade()
					.getTeachers().getName()
					+ "先生、");// 担当教員
			System.out.print(f_TimeTableData2.get(number).getClassRoom()
					+ "教室、");// 教室
			System.out.println(f_TimeTableData2.get(number).getClassOfGrade()
					.getCourseOrClass()
					+ "クラス");// コース・クラス
		}

		for (int number = 0; number < f_TimeTableData3.size(); number++) {
			System.out.print(f_TimeTableData3.get(number).getDayOfWeek()
					+ "曜日、");// 曜日
			System.out.print(f_TimeTableData3.get(number).getPeriod() + "限目、");// 限目
			System.out.print(f_TimeTableData3.get(number).getClassOfGrade()
					.getNumber()
					+ "コマ、");// コマ数
			System.out.print(f_TimeTableData3.get(number).getClassOfGrade()
					.getGrade()
					+ "年、");// 学年
			System.out.print(f_TimeTableData3.get(number).getClassOfGrade()
					.getPreviousOrLatter()
					+ "、");// 前期・後期
			System.out.print(f_TimeTableData3.get(number).getClassOfGrade()
					.getSubject()
					+ "、");// 科目名
			System.out.print(f_TimeTableData3.get(number).getClassOfGrade()
					.getTeachers().getName()
					+ "先生、");// 担当教員
			System.out.print(f_TimeTableData3.get(number).getClassRoom()
					+ "教室、");// 教室
			System.out.println(f_TimeTableData3.get(number).getClassOfGrade()
					.getCourseOrClass()
					+ "クラス");// コース・クラス
		}
	}

	/*
	 * 結果の表示
	 */
	private void result() {

		System.out.println("3次の時間割の結果");

		for (int number = 0; number < f_TimeTableData3.size(); number++) {
			System.out.print(f_TimeTableData3.get(number).getDayOfWeek()
					+ "曜日、");// 曜日
			System.out.print(f_TimeTableData3.get(number).getPeriod() + "限目、");// 限目
			System.out.print(f_TimeTableData3.get(number).getClassOfGrade()
					.getNumber()
					+ "コマ、");// コマ数
			System.out.print(f_TimeTableData3.get(number).getClassOfGrade()
					.getGrade()
					+ "年、");// 学年
			System.out.print(f_TimeTableData3.get(number).getClassOfGrade()
					.getPreviousOrLatter()
					+ "、");// 前期・後期
			System.out.print(f_TimeTableData3.get(number).getClassOfGrade()
					.getSubject()
					+ "、");// 科目名
			System.out.print(f_TimeTableData3.get(number).getClassOfGrade()
					.getTeachers().getName()
					+ "先生、");// 担当教員
			System.out.print(f_TimeTableData3.get(number).getClassRoom()
					+ "教室、");// 教室
			System.out.println(f_TimeTableData3.get(number).getClassOfGrade()
					.getCourseOrClass()
					+ "クラス");// コース・クラス
		}
	}

	// ------------------------------------------------------//
	// ---------------------評価値---------------------------//
	// ------------------------------------------------------//
	/*
	 * 曜日の評価値
	 * 
	 * @return 曜日の評価値
	 */
	private double getDayEvaluationValue() {

		double value = 1.0;

		for (int number = 0; number < f_NewTimeTableData3.size(); number++) {

			// 土曜日のとき
			if (Objects.equals("土", f_NewTimeTableData3.get(number)
					.getDayOfWeek())) {
				value *= 1.0;
			}

			// 月～金曜日のとき
			else {
				value *= 2.0;
			}
		}

		if (DEBUG) {
			System.out.println("曜日の評価値:" + value);
		}

		return value;
	}

	/*
	 * 担当者の授業のコマ数の評価値
	 * 
	 * @return 担当者の評価値
	 */
	private double getTeacherEvaluationValue() {

		double value = 1.0;

		for (int number = 0; number < f_NewTimeTableData3.size(); number++) {

		}

		return value;
	}

	/*
	 * 担当者の授業のコマ数の評価値
	 * 
	 * @param teacher 担当者
	 * 
	 * @return 担当者の評価値
	 */
	private double getTeacherEvaluationValue(String teacher) {

		double value = 1.0;

		double[] dayValue;
		dayValue = new double[MAX_DAY];

		for (int day = 0; day < dayValue.length; day++) {
			dayValue[day] = 0.0;// 0.0で初期化
		}

		for (int number = 0; number < f_NewTimeTableData3.size(); number++) {

			// 先生の名前が見つかったとき
			if (Objects.equals(teacher, f_NewTimeTableData3.get(number)
					.getClassOfGrade().getTeachers().getName())) {

				dayValue[TimeTable.changeDayToValue(f_NewTimeTableData3.get(
						number).getDayOfWeek())] += 1.0;
			}

			// 先生の名前が見つかったとき
			if (Objects.equals("各先生", f_NewTimeTableData3.get(number)
					.getClassOfGrade().getTeachers().getName())) {

				dayValue[TimeTable.changeDayToValue(f_NewTimeTableData3.get(
						number).getDayOfWeek())] += 1.0;
			}
		}

		for (int day = 0; day < dayValue.length; day++) {
			value *= dayValue[day];
		}

		if (DEBUG) {
			System.out.println(teacher + "先生の評価値:" + value);
		}

		return value;
	}

	// /*
	// * 全体の評価値
	// *
	// * @param teacher 先生の名前
	// *
	// * @return 全体の評価値
	// */
	// private double getEvaluationValue(String teacher) {
	//
	// return getDayEvaluationValue() * getTeacherEvaluationValue(teacher);
	// }

	/*
	 * 評価値の計算をする
	 */
	private void calcEvaluationValue() {
		f_NewEvaluationValue = getDayEvaluationValue();

		for (int num = 0; num < f_TeacherData.size(); num++) {
			f_NewEvaluationValue *= getTeacherEvaluationValue(f_TeacherData
					.get(num).getName());
		}
	}

	// ------------------------------------------------------//
	// ---------------------遺伝的アルゴリズム---------------//
	// ------------------------------------------------------//
	/*
	 * 3次の時間割の作成
	 */
	private void makeTimeTable3() {
		setClassOfGrade3ToFirstTimeTable3();// 3次の学年ごとの授業を最初の時間割にセットする
//		exeGeneticAlgo();// 遺伝的アルゴリズムを実行する
	}

	/*
	 * 遺伝的アルゴリズムを実行する
	 */
	private void exeGeneticAlgo() {

		for (int num = 0; num < CHECK_NUM; num++) {

			System.out.println("遺伝的アルゴリズム" + (num + 1) + "回目");

			// 交叉できるようになるまで繰り返す
			do {
				choice();// 前期後期どちらかの何曜日の何限目の何学年かを選択する
			} while (!canCross());

			cross();// 交叉する

			mutation();// 突然変異をする

			calcEvaluationValue();// 評価値の計算をする

			// 新しい評価値が上のとき
			if (f_OldEvaluationValue < f_NewEvaluationValue) {

				for (int number = 0; number < f_NewTimeTableData3.size(); number++) {

					f_TimeTableData3.set(number,
							f_NewTimeTableData3.get(number));// 前の時間割のデータを更新する
				}

				f_OldEvaluationValue = f_NewEvaluationValue;// 評価値を更新する

				if (DEBUG) {
					System.out.println("評価値が更新された。");
					System.out.println("評価値:" + f_NewEvaluationValue);
				}
			}

			// 新しい評価値以下のとき
			else {

				for (int number = 0; number < f_TimeTableData3.size(); number++) {

					f_NewTimeTableData3.set(number,
							f_TimeTableData3.get(number));// 前の時間割のデータに戻す
				}

				if (DEBUG) {
					System.out.println("評価値は更新されない。");
					System.out.println("評価値:" + f_OldEvaluationValue);
				}
			}
		}
	}

	/*
	 * 前期後期どちらかの何曜日の何限目の何学年かを選択
	 */
	private void choice() {

		// 曜日をランダムに選択
		f_RandomDayOfWeek1 = Calculation.getRnd(
				TimeTable.changeDayToValue("月"),
				TimeTable.changeDayToValue("土"));

		// 限目をランダムに選択
		f_RandomPeriod1 = Calculation.getRnd(1, 5);

		// 学年をランダムに選択
		f_RandomGrade1 = Calculation.getRnd(1, 4);

		// 前期か後期かをランダムに選択
		f_RandomPreviousOrLatter1 = Calculation.getRnd(
				ClassOfGrade.changePreviousOrLatterToValue("前期"),
				ClassOfGrade.changePreviousOrLatterToValue("後期"));

		// 時間割3のデータに存在するとき
		if (existInTimeTable(f_TimeTableData3, f_RandomDayOfWeek1,
				f_RandomPeriod1, f_RandomGrade1, f_RandomPreviousOrLatter1,
				f_TimeTableDataTmp1, f_TmpNumber1)) {

			if (DEBUG) {
				System.out.println("時間割3のデータに存在する");

				System.out.print(f_TimeTableDataTmp1.getDayOfWeek() + "曜日、");
				System.out.print(f_TimeTableDataTmp1.getPeriod() + "限目、");
				System.out.print(f_TimeTableDataTmp1.getClassOfGrade()
						.getNumber() + "コマ、");
				System.out.print(f_TimeTableDataTmp1.getClassOfGrade()
						.getGrade() + "年、");
				System.out.print(f_TimeTableDataTmp1.getClassOfGrade()
						.getPreviousOrLatter() + "、");
				System.out.print(f_TimeTableDataTmp1.getClassOfGrade()
						.getSubject() + "、");
				System.out.print(f_TimeTableDataTmp1.getClassOfGrade()
						.getTeachers().getName()
						+ "先生、");
				System.out.print(f_TimeTableDataTmp1.getClassRoom() + "教室、");
				System.out.println(f_TimeTableDataTmp1.getClassOfGrade()
						.getCourseOrClass() + "クラス");
			}
		}

		// 時間割3のデータに存在しないとき
		else {

			if (DEBUG) {
				System.out.println("時間割3のデータに存在しない");
			}
		}

		// 時間割3のデータに存在しない
		// また、1つ目で選択したデータと被る
		// 上記の条件のとき繰り返す
		do {

			// 曜日をランダムに選択
			f_RandomDayOfWeek2 = Calculation.getRnd(
					TimeTable.changeDayToValue("月"),
					TimeTable.changeDayToValue("土"));

			// 限目をランダムに選択
			f_RandomPeriod2 = Calculation.getRnd(1, 5);

			// 学年をランダムに選択
			f_RandomGrade2 = Calculation.getRnd(1, 4);

			// 前期か後期かをランダムに選択
			f_RandomPreviousOrLatter2 = Calculation.getRnd(
					ClassOfGrade.changePreviousOrLatterToValue("前期"),
					ClassOfGrade.changePreviousOrLatterToValue("後期"));

		} while (!existInTimeTable(f_TimeTableData3, f_RandomDayOfWeek2,
				f_RandomPeriod2, f_RandomGrade2, f_RandomPreviousOrLatter2,
				f_TimeTableDataTmp2, f_TmpNumber2)
				|| ((f_RandomDayOfWeek1 == f_RandomDayOfWeek2)
						&& (f_RandomPeriod1 == f_RandomPeriod2) && (f_RandomGrade1 == f_RandomGrade2)));
	}

	/*
	 * 既存の時間割のデータに存在するか
	 * 
	 * @param time_table_data 既存の時間割のデータ
	 * 
	 * @param day_of_week 曜日
	 * 
	 * @param period 限目
	 * 
	 * @param grade 学年
	 * 
	 * @param previous_latter 前期後期
	 * 
	 * @param time_table 新規の時間割のデータ
	 * 
	 * @param tmp_number 交換用の番号(時間割のデータの要素番号)
	 * 
	 * @return true 存在する
	 * 
	 * @return false 存在しない
	 */
	private boolean existInTimeTable(ArrayList<TimeTable> time_table_data,
			int day_of_week, int period, int grade, int previous_latter,
			TimeTable new_time_table, int tmp_number) {

		for (int number = 0; number < time_table_data.size(); number++) {

			// ランダムに選択した曜日、限目、学年、前期後期が時間割3のデータに存在するとき
			if (Objects.equals(time_table_data.get(number).getDayOfWeek(),
					TimeTable.changeValueToDay(day_of_week))
					&& time_table_data.get(number).getPeriod() == period
					&& time_table_data.get(number).getClassOfGrade().getGrade() == grade
					&& Objects
							.equals(time_table_data.get(number)
									.getClassOfGrade().getPreviousOrLatter(),
									ClassOfGrade
											.changeValueToPreviousOrLatter(previous_latter))) {

				// 新規の時間割のデータとしてセットする
				new_time_table = time_table_data.get(number);
				// new_time_table.setDayOfWeek(TimeTable
				// .changeValueToDay(day_of_week));
				// new_time_table.setPeriod(period);
				// new_time_table.getClassOfGrade().setGrade(grade);
				// new_time_table.getClassOfGrade().setNumber(
				// time_table_data.get(number).getClassOfGrade().getNumber());//
				// コマ数
				// new_time_table.getClassOfGrade().setPreviousOrLatter(
				// time_table_data.get(number).getClassOfGrade().getPreviousOrLatter());//
				// 前期後期
				// new_time_table.getClassOfGrade().setSubject(time_table_data.get(number).getClassOfGrade().getSubject());//
				// 科目名
				// new_time_table.getClassOfGrade().getTeachers()
				// .setName(time_table_data.get(number).getClassOfGrade().getTeachers().getName());//
				// 担当教員
				// new_time_table.setClassRoom(time_table_data.get(number).getClassRoom());//
				// 教室
				tmp_number = number;

				if (DEBUG) {
					System.out.println("既存の時間割のデータに存在します。");
					System.out.print(new_time_table.getDayOfWeek() + "曜日、");// 曜日
					System.out.print(new_time_table.getPeriod() + "限目、");// 限目
					System.out.print(new_time_table.getClassOfGrade()
							.getNumber() + "コマ、");// コマ数
					System.out.print(new_time_table.getClassOfGrade()
							.getGrade() + "年、");// 学年
					System.out.print(new_time_table.getClassOfGrade()
							.getPreviousOrLatter() + "、");// 前期・後期
					System.out.print(new_time_table.getClassOfGrade()
							.getSubject() + "、");// 科目名
					System.out.print(new_time_table.getClassOfGrade()
							.getTeachers().getName()
							+ "先生、");// 担当教員
					System.out.print(new_time_table.getClassRoom() + "教室、");// 教室
					System.out.println(new_time_table.getClassOfGrade()
							.getCourseOrClass() + "クラス");// コース・クラス
				}
				return true;
			}
		}

		if (DEBUG) {
			System.out.println("既存の時間割のデータに存在しません。");
		}

		return false;
	}

	/*
	 * 交叉できるか
	 * 
	 * @return true 交叉できる
	 * 
	 * @return false 交叉できない
	 */
	private boolean canCross() {

		TimeTable tmpTimeTableData = new TimeTable();

//		// 試しに交叉
//		tmpTimeTableData.setTimeTable(f_TimeTableDataTmp1.getDayOfWeek(),
//				f_TimeTableDataTmp1.getPeriod(),
//				f_TimeTableDataTmp1.getClassOfGrade(),
//				f_TimeTableDataTmp1.getClassRoom());
//		;

		if (DEBUG) {
			System.out.print(tmpTimeTableData.getDayOfWeek() + "曜日、");// 曜日
			System.out.print(tmpTimeTableData.getPeriod() + "限目、");// 限目
			System.out.print(tmpTimeTableData.getClassOfGrade().getNumber()
					+ "コマ、");// コマ数
			System.out.print(tmpTimeTableData.getClassOfGrade().getGrade()
					+ "年、");// 学年
			System.out.print(tmpTimeTableData.getClassOfGrade()
					.getPreviousOrLatter() + "、");// 前期・後期
			System.out.print(tmpTimeTableData.getClassOfGrade().getSubject()
					+ "、");// 科目名
			System.out.print(tmpTimeTableData.getClassOfGrade().getTeachers()
					.getName()
					+ "先生、");// 担当教員
			System.out.print(tmpTimeTableData.getClassRoom() + "教室、");// 教室
			System.out.println(tmpTimeTableData.getClassOfGrade()
					.getCourseOrClass() + "クラス");// コース・クラス
		}
		
//		f_TimeTableDataTmp1.setTimeTable(f_TimeTableDataTmp2.getDayOfWeek(),
//				f_TimeTableDataTmp2.getPeriod(),
//				f_TimeTableDataTmp2.getClassOfGrade(),
//				f_TimeTableDataTmp2.getClassRoom());
//		f_TimeTableDataTmp1 = f_TimeTableDataTmp2;
		
//		TimeTable.exchangeTimeTable(f_TimeTableDataTmp1, f_TimeTableDataTmp2);
//		f_TimeTableDataTmp2.setTimeTable(tmpTimeTableData.getDayOfWeek(),
//				tmpTimeTableData.getPeriod(),
//				tmpTimeTableData.getClassOfGrade(),
//				tmpTimeTableData.getClassRoom());
//		f_TimeTableDataTmp2 = tmpTimeTableData;

		// 1次の時間割のデータと重複するとき
		if (checkDuplication1(f_TimeTableDataTmp1)) {

			if (DEBUG) {
				System.out
						.println("1次の時間割のデータと重複するため交叉できない(f_TimeTableDataTmp1)");
			}

			return false;
		}

		// 2次の時間割のデータと重複するとき
		if (checkDuplication2(f_TimeTableDataTmp1)) {

			if (DEBUG) {
				System.out
						.println("2次の時間割のデータと重複するため交叉できない(f_TimeTableDataTmp1)");
			}

			return false;
		}

		// 1次の時間割のデータと重複するとき
		if (checkDuplication1(f_TimeTableDataTmp2)) {

			if (DEBUG) {
				System.out
						.println("1次の時間割のデータと重複するため交叉できない(f_TimeTableDataTmp2)");
			}

			return false;
		}

		// 2次の時間割のデータと重複するとき
		if (checkDuplication2(f_TimeTableDataTmp2)) {

			if (DEBUG) {
				System.out
						.println("2次の時間割のデータと重複するため交叉できない(f_TimeTableDataTmp2)");
			}

			return false;
		}

		if (DEBUG) {
			System.out.println("交叉できる");
		}

		return true;
	}

	/*
	 * 交叉する
	 */
	private void cross() {

		// 交叉
		f_NewTimeTableData3.get(f_TmpNumber1).setPeriod(
				f_TimeTableDataTmp2.getPeriod());
		f_NewTimeTableData3.get(f_TmpNumber1).setDayOfWeek(
				f_TimeTableDataTmp2.getDayOfWeek());

		f_NewTimeTableData3.get(f_TmpNumber2).setPeriod(
				f_TimeTableDataTmp1.getPeriod());
		f_NewTimeTableData3.get(f_TmpNumber2).setDayOfWeek(
				f_TimeTableDataTmp1.getDayOfWeek());

	}

	/*
	 * 突然変異する
	 */
	private void mutation() {

		int num = Calculation
				.getRnd(1, ORDER1_COLS + ORDER2_COLS + ORDER3_COLS);// 3次の科目数/全科目数の確率

		// 1から3次の科目数までのとき
		if (num <= ORDER3_COLS) {

			if (DEBUG) {
				System.out.println("突然変異発生");
			}

			// 時間割のデータと重複しないようになるまで繰り返す
			do {
				f_TmpNumber1 = Calculation.getRnd(0,
						f_NewTimeTableData3.size() - 1);// 要素番号をランダムに選択
				f_TimeTableDataTmp1 = f_NewTimeTableData3.get(f_TmpNumber1);// 新規の3次の時間割のデータを選択
				f_TimeTableDataTmp1.setDayOfWeek(TimeTable
						.changeValueToDay(Calculation.getRnd(
								TimeTable.changeDayToValue("月"),
								TimeTable.changeDayToValue("土"))));// 曜日をランダムに選択
				f_TimeTableDataTmp1.setPeriod(Calculation.getRnd(1, 5));// 限目をランダムに選択
				f_TimeTableDataTmp1.setClassRoom(TimeTable
						.changeValueToDay(Calculation.getRnd(
								TimeTable.changeRoomToValue("31-202"),
								TimeTable.changeRoomToValue("31-803"))));// 教室をランダムに選択

			} while (checkDuplication(f_TimeTableDataTmp1));

			f_NewTimeTableData3.get(f_TmpNumber1).setDayOfWeek(
					f_TimeTableDataTmp1.getDayOfWeek());// 曜日
			f_NewTimeTableData3.get(f_TmpNumber1).setPeriod(
					f_TimeTableDataTmp1.getPeriod());// 限目
			f_NewTimeTableData3.get(f_TmpNumber1).setClassRoom(
					f_TimeTableDataTmp1.getClassRoom());// 教室
		}

		// 3次の科目数を超えた数のとき
		else {

			if (DEBUG) {
				System.out.println("突然変異なし");
			}
		}
	}

	/*
	 * 重複がないかのチェック
	 * 
	 * @param number 番目(3次の時間割)
	 * 
	 * @return true 重複あり
	 * 
	 * @return false 重複なし
	 */
	private boolean checkDuplication(TimeTable time_table) {

		if (DEBUG) {
			System.out.print(time_table.getDayOfWeek() + "曜日、");// 曜日
			System.out.print(time_table.getPeriod() + "限目、");// 限目
			System.out.print(time_table.getClassOfGrade().getNumber() + "コマ、");// コマ数
			System.out.print(time_table.getClassOfGrade().getGrade() + "年、");// 学年
			System.out.print(time_table.getClassOfGrade().getPreviousOrLatter()
					+ "、");// 前期・後期
			System.out.print(time_table.getClassOfGrade().getSubject() + "、");// 科目名
			System.out.print(time_table.getClassOfGrade().getTeachers()
					.getName()
					+ "先生、");// 担当教員
			System.out.print(time_table.getClassRoom() + "教室、");// 教室
			System.out.println(time_table.getClassOfGrade().getCourseOrClass()
					+ "クラス");// コース・クラス
		}

		// 1次の時間割と重複するとき
		if (checkDuplication1(time_table)) {

			if (DEBUG) {
				System.out.println("1次と重複あり\n");
			}
			return true;
		}

		if (DEBUG) {
			System.out.println("1次と重複なし\n");
		}

		// 2次の時間割と重複するとき
		if (checkDuplication2(time_table)) {

			if (DEBUG) {
				System.out.println("2次と重複あり\n");
			}
			return true;
		}

		if (DEBUG) {
			System.out.println("2次と重複なし\n");
		}

		// 3次の時間割と重複するとき
		if (checkDuplication3(time_table)) {

			if (DEBUG) {
				System.out.println("3次と重複あり\n");
			}
			return true;
		}

		if (DEBUG) {
			System.out.println("3次と重複なし\n");
		}

		return false;
	}

	/*
	 * 1次の時間割と重複がないかのチェック
	 * 
	 * @param number 番目(3次の時間割)
	 * 
	 * @return true 重複あり
	 * 
	 * @return false 重複なし
	 */
	private boolean checkDuplication1(TimeTable time_table) {

		for (int number1 = 0; number1 < f_TimeTableData1.size(); number1++) {

			// 同じ曜日のとき
			if (Objects.equals(f_TimeTableData1.get(number1).getDayOfWeek(),
					time_table.getDayOfWeek())) {

				// 同じ前期・後期のとき
				if (Objects.equals(f_TimeTableData1.get(number1)
						.getClassOfGrade().getPreviousOrLatter(), time_table
						.getClassOfGrade().getPreviousOrLatter())) {

					// 同じ学年のとき
					if (f_TimeTableData1.get(number1).getClassOfGrade()
							.getGrade() == time_table.getClassOfGrade()
							.getGrade()) {

						for (int addPeriod = 0; addPeriod < f_TimeTableData1
								.get(number1).getClassOfGrade().getNumber(); addPeriod++) {

							// 同じ限目のとき
							if (f_TimeTableData1.get(number1).getPeriod()
									+ addPeriod == time_table.getPeriod()) {

								// 同じ先生のとき
								if (Objects.equals(f_TimeTableData1
										.get(number1).getClassOfGrade()
										.getTeachers().getName(), time_table
										.getClassOfGrade().getTeachers()
										.getName())) {

									if (DEBUG) {
										System.out.println("先生と重複");
									}
									return true;
								}

								// 同じ教室のとき
								if (Objects.equals(f_TimeTableData1
										.get(number1).getClassRoom(),
										time_table.getClassRoom())) {

									if (DEBUG) {
										System.out.println("教室と重複");
									}
									return true;
								}

								// コース・クラスと重複するとき
								if (checkDuplicationCouseOrClass(
										f_TimeTableData1.get(number1)
												.getClassOfGrade()
												.getCourseOrClass(), time_table
												.getClassOfGrade()
												.getCourseOrClass())) {

									if (DEBUG) {
										System.out.println("コース・クラスと重複");
									}
									return true;
								}
							}
						}
					}

					// 違う学年のとき
					else {

						for (int addPeriod = 0; addPeriod < f_TimeTableData1
								.get(number1).getClassOfGrade().getNumber(); addPeriod++) {

							// 同じ限目のとき
							if (f_TimeTableData1.get(number1).getPeriod()
									+ addPeriod == time_table.getPeriod()) {

								// 同じ先生のとき
								if (Objects.equals(f_TimeTableData1
										.get(number1).getClassOfGrade()
										.getTeachers().getName(), time_table
										.getClassOfGrade().getTeachers()
										.getName())) {

									if (DEBUG) {
										System.out.println("先生と重複(違う学年)");
									}
									return true;
								}

								// 同じ教室のとき
								if (Objects.equals(f_TimeTableData1
										.get(number1).getClassRoom(),
										time_table.getClassRoom())) {
									if (DEBUG) {
										System.out.println("教室と重複(違う学年)");
									}
									return true;
								}
							}
						}
					}
				}
			}
		}

		return false;
	}

	/*
	 * 2次の時間割と重複がないかのチェック
	 * 
	 * @param number 番目(3次の時間割)
	 * 
	 * @return true 重複あり
	 * 
	 * @return false 重複なし
	 */
	private boolean checkDuplication2(TimeTable time_table) {

		for (int number2 = 0; number2 < f_TimeTableData2.size(); number2++) {

			// 同じ曜日のとき
			if (Objects.equals(f_TimeTableData2.get(number2).getDayOfWeek(),
					time_table.getDayOfWeek())) {

				// 同じ前期・後期のとき
				if (Objects.equals(f_TimeTableData2.get(number2)
						.getClassOfGrade().getPreviousOrLatter(), time_table
						.getClassOfGrade().getPreviousOrLatter())) {

					// 同じ学年のとき
					if (f_TimeTableData2.get(number2).getClassOfGrade()
							.getGrade() == time_table.getClassOfGrade()
							.getGrade()) {

						for (int addPeriod = 0; addPeriod < f_TimeTableData2
								.get(number2).getClassOfGrade().getNumber(); addPeriod++) {

							// 同じ限目のとき
							if (f_TimeTableData2.get(number2).getPeriod()
									+ addPeriod == time_table.getPeriod()) {

								if (DEBUG) {
									System.out.println("学年が重複");
								}

								return true;
							}
						}
					}

					// 違う学年のとき
					else {

						for (int addPeriod = 0; addPeriod < f_TimeTableData2
								.get(number2).getClassOfGrade().getNumber(); addPeriod++) {

							// 同じ限目のとき
							if (f_TimeTableData2.get(number2).getPeriod()
									+ addPeriod == time_table.getPeriod()) {

								// 同じ先生のとき
								if (Objects.equals(f_TimeTableData2
										.get(number2).getClassOfGrade()
										.getTeachers().getName(), time_table
										.getClassOfGrade().getTeachers()
										.getName())) {

									if (DEBUG) {
										System.out.println("先生と重複(違う学年)");
									}
									return true;
								}

								// 同じ教室のとき
								if (Objects.equals(f_TimeTableData2
										.get(number2).getClassRoom(),
										time_table.getClassRoom())) {
									if (DEBUG) {
										System.out.println("教室と重複(違う学年)");
									}
									return true;
								}
							}
						}
					}
				}
			}
		}
		return false;
	}

	/*
	 * 3次の時間割と重複がないかのチェック
	 * 
	 * @param number 番目(3次の時間割)
	 * 
	 * @return true 重複あり
	 * 
	 * @return false 重複なし
	 */
	private boolean checkDuplication3(TimeTable time_table) {

		// 会議の曜日のとき
		if (Objects.equals(CONFERENCE_DAY, time_table.getDayOfWeek())) {

			// 会議の限目のとき
			if (time_table.getPeriod() == CONFERENCE_PERIOD) {

				if (DEBUG) {
					System.out.println("会議の日程と重複");
				}
				return true;
			}
		}

		for (int number3 = 0; number3 < f_TimeTableData3.size(); number3++) {

			// 同じ曜日のとき
			if (f_TimeTableData3.get(number3).getDayOfWeek()
					.equals(time_table.getDayOfWeek())) {

				// 同じ前期・後期のとき
				if (f_TimeTableData3
						.get(number3)
						.getClassOfGrade()
						.getPreviousOrLatter()
						.equals(time_table.getClassOfGrade()
								.getPreviousOrLatter())) {

					// 同じ学年のとき
					if (f_TimeTableData3.get(number3).getClassOfGrade()
							.getGrade() == time_table.getClassOfGrade()
							.getGrade()) {

						for (int addPeriod = 0; addPeriod < f_TimeTableData3
								.get(number3).getClassOfGrade().getNumber(); addPeriod++) {

							// 同じ限目のとき
							if (f_TimeTableData3.get(number3).getPeriod()
									+ addPeriod == time_table.getPeriod()) {

								// 同じ先生のとき
								if (f_TimeTableData3
										.get(number3)
										.getClassOfGrade()
										.getTeachers()
										.getName()
										.equals(time_table.getClassOfGrade()
												.getTeachers().getName())) {

									if (DEBUG) {
										System.out.println("先生と重複");
									}
									return true;
								}

								// 同じ教室のとき
								if (f_TimeTableData3.get(number3)
										.getClassRoom()
										.equals(time_table.getClassRoom())) {
									if (DEBUG) {
										System.out.println("教室と重複");
									}
									return true;
								}

								// コース・クラスと重複するとき
								if (checkDuplicationCouseOrClass(
										f_TimeTableData3.get(number3)
												.getClassOfGrade()
												.getCourseOrClass(), time_table
												.getClassOfGrade()
												.getCourseOrClass())) {

									if (DEBUG) {
										System.out.println("コース・クラスと重複");
									}
									return true;
								}
							}
						}
					}

					// 違う学年のとき
					else {

						for (int addPeriod = 0; addPeriod < f_TimeTableData3
								.get(number3).getClassOfGrade().getNumber(); addPeriod++) {

							// 同じ限目のとき
							if (f_TimeTableData3.get(number3).getPeriod()
									+ addPeriod == time_table.getPeriod()) {

								// 同じ先生のとき
								if (f_TimeTableData3
										.get(number3)
										.getClassOfGrade()
										.getTeachers()
										.getName()
										.equals(time_table.getClassOfGrade()
												.getTeachers().getName())) {

									if (DEBUG) {
										System.out.println("先生と重複(違う学年)");
									}
									return true;
								}

								// 同じ教室のとき
								if (f_TimeTableData3.get(number3)
										.getClassRoom()
										.equals(time_table.getClassRoom())) {
									if (DEBUG) {
										System.out.println("教室と重複(違う学年)");
									}
									return true;
								}
							}
						}
					}
				}
			}
		}

		return false;
	}

	/*
	 * 学年ごとの授業を最初の時間割にセットする
	 */
	private void setClassOfGrade3ToFirstTimeTable3() {
		TimeTable timeTableData = new TimeTable();

		for (int number = 0; number < f_ClassOfGradeData3.size(); number++) {
			timeTableData = new TimeTable();
			timeTableData.setClassOfGrade(f_ClassOfGradeData3.get(number));
			f_TimeTableData3.add(timeTableData);

			// 重複がある限り繰り返す
			do {
				makeFirstTimeTable3(number);// 最初の3次の時間割を決める

			} while (checkDuplication(number));

		}

		// 新規の3次の時間割のデータとしてセット
		for (int number = 0; number < f_TimeTableData3.size(); number++) {
			f_NewTimeTableData3.add(f_TimeTableData3.get(number));
		}
	}

	/*
	 * 最初の3次の時間割を決める
	 * 
	 * @param number 番目
	 */
	private void makeFirstTimeTable3(int number) {

		// ランダムに決定
		int randomDay = Calculation.getRnd(TimeTable.changeDayToValue("月"),
				TimeTable.changeDayToValue("土"));// 曜日をランダムに決定
		int randomPeriod = Calculation.getRnd(1, 5);// 限目をランダムに決定
		int randomClassRoom = Calculation.getRnd(
				TimeTable.changeRoomToValue("31-202"),
				TimeTable.changeRoomToValue("31-803"));// 教室をランダムに決定

		// 3次の時間割のデータとして設定する
		f_TimeTableData3.get(number).setDayOfWeek(
				TimeTable.changeValueToDay(randomDay));// 曜日を設定
		f_TimeTableData3.get(number).setPeriod(randomPeriod);// 限目を設定
		f_TimeTableData3.get(number).setClassRoom(
				TimeTable.changeValueToRoom(randomClassRoom));// 教室を設定
	}

	/*
	 * 重複がないかのチェック
	 * 
	 * @param number 番目(3次の時間割)
	 * 
	 * @return true 重複あり
	 * 
	 * @return false 重複なし
	 */
	private boolean checkDuplication(int number) {

		if (DEBUG) {
			System.out.print(f_TimeTableData3.get(number).getDayOfWeek()
					+ "曜日、");// 曜日
			System.out.print(f_TimeTableData3.get(number).getPeriod() + "限目、");// 限目
			System.out.print(f_TimeTableData3.get(number).getClassOfGrade()
					.getNumber()
					+ "コマ、");// コマ数
			System.out.print(f_TimeTableData3.get(number).getClassOfGrade()
					.getGrade()
					+ "年、");// 学年
			System.out.print(f_TimeTableData3.get(number).getClassOfGrade()
					.getPreviousOrLatter()
					+ "、");// 前期・後期
			System.out.print(f_TimeTableData3.get(number).getClassOfGrade()
					.getSubject()
					+ "、");// 科目名
			System.out.print(f_TimeTableData3.get(number).getClassOfGrade()
					.getTeachers().getName()
					+ "先生、");// 担当教員
			System.out.print(f_TimeTableData3.get(number).getClassRoom()
					+ "教室、");// 教室
			System.out.println(f_TimeTableData3.get(number).getClassOfGrade()
					.getCourseOrClass()
					+ "クラス");// コース・クラス
		}

		// 1次の時間割と重複するとき
		if (checkDuplication1(number)) {
			if (DEBUG) {
				System.out.println("1次と重複あり\n");
			}
			return true;
		}

		if (DEBUG) {
			System.out.println("1次と重複なし\n");
		}

		// 2次の時間割と重複するとき
		if (checkDuplication2(number)) {
			if (DEBUG) {
				System.out.println("2次と重複あり\n");
			}
			return true;
		}

		if (DEBUG) {
			System.out.println("2次と重複なし\n");
		}

		// 3次の時間割と重複するとき
		if (checkDuplication3(number)) {
			if (DEBUG) {
				System.out.println("3次と重複あり\n");
			}
			return true;
		}

		if (DEBUG) {
			System.out.println("3次と重複なし\n");
			// System.out.println("");
		}

		return false;
	}

	/*
	 * 1次の時間割と重複がないかのチェック
	 * 
	 * @param number 番目(3次の時間割)
	 * 
	 * @return true 重複あり
	 * 
	 * @return false 重複なし
	 */
	private boolean checkDuplication1(int number) {

		for (int number1 = 0; number1 < f_TimeTableData1.size(); number1++) {

			// 同じ曜日のとき
			if (Objects.equals(f_TimeTableData1.get(number1).getDayOfWeek(),
					f_TimeTableData3.get(number).getDayOfWeek())) {

				// 同じ前期・後期のとき
				if (Objects.equals(f_TimeTableData1.get(number1)
						.getClassOfGrade().getPreviousOrLatter(),
						f_TimeTableData3.get(number).getClassOfGrade()
								.getPreviousOrLatter())) {

					// 同じ学年のとき
					if (f_TimeTableData1.get(number1).getClassOfGrade()
							.getGrade() == f_TimeTableData3.get(number)
							.getClassOfGrade().getGrade()) {

						for (int addPeriod = 0; addPeriod < f_TimeTableData1
								.get(number1).getClassOfGrade().getNumber(); addPeriod++) {

							// 同じ限目のとき
							if (f_TimeTableData1.get(number1).getPeriod()
									+ addPeriod == f_TimeTableData3.get(number)
									.getPeriod()) {

								// 同じ先生のとき
								if (Objects.equals(f_TimeTableData1
										.get(number1).getClassOfGrade()
										.getTeachers().getName(),
										f_TimeTableData3.get(number)
												.getClassOfGrade()
												.getTeachers().getName())) {

									if (DEBUG) {
										System.out.println("先生と重複");
									}
									return true;
								}

								// 同じ教室のとき
								if (Objects.equals(f_TimeTableData1
										.get(number1).getClassRoom(),
										f_TimeTableData3.get(number)
												.getClassRoom())) {

									if (DEBUG) {
										System.out.println("教室と重複");
									}
									return true;
								}

								// コース・クラスが重複するとき
								if (checkDuplicationCouseOrClass(
										f_TimeTableData1.get(number1)
												.getClassOfGrade()
												.getCourseOrClass(),
										f_TimeTableData3.get(number)
												.getClassOfGrade()
												.getCourseOrClass())) {

									if (DEBUG) {
										System.out.println("コース・クラスと重複");
									}
									return true;
								}
							}
						}
					}

					// 違う学年のとき
					else {

						for (int addPeriod = 0; addPeriod < f_TimeTableData1
								.get(number1).getClassOfGrade().getNumber(); addPeriod++) {

							// 同じ限目のとき
							if (f_TimeTableData1.get(number1).getPeriod()
									+ addPeriod == f_TimeTableData3.get(number)
									.getPeriod()) {

								// 同じ先生のとき
								if (Objects.equals(f_TimeTableData1
										.get(number1).getClassOfGrade()
										.getTeachers().getName(),
										f_TimeTableData3.get(number)
												.getClassOfGrade()
												.getTeachers().getName())) {

									if (DEBUG) {
										System.out.println("先生と重複(違う学年)");
									}
									return true;
								}

								// 同じ教室のとき
								if (Objects.equals(f_TimeTableData1
										.get(number1).getClassRoom(),
										f_TimeTableData3.get(number)
												.getClassRoom())) {
									if (DEBUG) {
										System.out.println("教室と重複(違う学年)");
									}
									return true;
								}
							}
						}
					}
				}
			}
		}

		return false;
	}

	/*
	 * 2次の時間割と重複がないかのチェック
	 * 
	 * @param number 番目(3次の時間割)
	 * 
	 * @return true 重複あり
	 * 
	 * @return false 重複なし
	 */
	private boolean checkDuplication2(int number) {

		for (int number2 = 0; number2 < f_TimeTableData2.size(); number2++) {

			// 同じ曜日のとき
			if (Objects.equals(f_TimeTableData2.get(number2).getDayOfWeek(),
					f_TimeTableData3.get(number).getDayOfWeek())) {

				// 同じ前期・後期のとき
				if (Objects.equals(f_TimeTableData2.get(number2)
						.getClassOfGrade().getPreviousOrLatter(),
						f_TimeTableData3.get(number).getClassOfGrade()
								.getPreviousOrLatter())) {

					// 同じ学年のとき
					if (f_TimeTableData2.get(number2).getClassOfGrade()
							.getGrade() == f_TimeTableData3.get(number)
							.getClassOfGrade().getGrade()) {

						for (int addPeriod = 0; addPeriod < f_TimeTableData2
								.get(number2).getClassOfGrade().getNumber(); addPeriod++) {

							// 同じ限目のとき
							if (f_TimeTableData2.get(number2).getPeriod()
									+ addPeriod == f_TimeTableData3.get(number)
									.getPeriod()) {

								if (DEBUG) {
									System.out.println("限目と重複(同じ学年)");
								}

								return true;
							}

						}
					}

					// 違う学年のとき
					else {

						for (int addPeriod = 0; addPeriod < f_TimeTableData2
								.get(number2).getClassOfGrade().getNumber(); addPeriod++) {

							// 同じ限目のとき
							if (f_TimeTableData2.get(number2).getPeriod()
									+ addPeriod == f_TimeTableData3.get(number)
									.getPeriod()) {

								// 同じ先生のとき
								if (Objects.equals(f_TimeTableData2
										.get(number2).getClassOfGrade()
										.getTeachers().getName(),
										f_TimeTableData3.get(number)
												.getClassOfGrade()
												.getTeachers().getName())) {

									if (DEBUG) {
										System.out.println("先生と重複(違う学年)");
									}
									return true;
								}

								// 同じ教室のとき
								if (Objects.equals(f_TimeTableData2
										.get(number2).getClassRoom(),
										f_TimeTableData3.get(number)
												.getClassRoom())) {
									if (DEBUG) {
										System.out.println("教室と重複(違う学年)");
									}
									return true;
								}
							}
						}
					}
				}
			}
		}
		return false;
	}

	/*
	 * 3次の時間割と重複がないかのチェック
	 * 
	 * @param number 番目(3次の時間割)
	 * 
	 * @return true 重複あり
	 * 
	 * @return false 重複なし
	 */
	private boolean checkDuplication3(int number) {

		// // 会議の曜日のとき
		// if (Objects.equals(CONFERENCE_DAY, f_TimeTableData3.get(number)
		// .getDayOfWeek())) {
		//
		// // 会議の限目のとき
		// if (f_TimeTableData3.get(number).getPeriod() == CONFERENCE_PERIOD) {
		//
		// if (DEBUG) {
		// System.out.println("会議の日程と重複");
		// }
		// return true;
		// }
		// }

		for (int number3 = 0; number3 < f_TimeTableData3.size(); number3++) {

			if (number <= number3) {
				break;
			}

			// 同じ曜日のとき
			if (Objects.equals(f_TimeTableData3.get(number3).getDayOfWeek(),
					f_TimeTableData3.get(number).getDayOfWeek())) {

				// 同じ前期・後期のとき
				if (Objects.equals(f_TimeTableData3.get(number3)
						.getClassOfGrade().getPreviousOrLatter(),
						f_TimeTableData3.get(number).getClassOfGrade()
								.getPreviousOrLatter())) {

					// 同じ学年のとき
					if (f_TimeTableData3.get(number3).getClassOfGrade()
							.getGrade() == f_TimeTableData3.get(number)
							.getClassOfGrade().getGrade()) {

						for (int addPeriod = 0; addPeriod < f_TimeTableData3
								.get(number3).getClassOfGrade().getNumber(); addPeriod++) {

							// 同じ限目のとき
							if (f_TimeTableData3.get(number3).getPeriod()
									+ addPeriod == f_TimeTableData3.get(number)
									.getPeriod()) {

								// 同じ先生のとき
								if (Objects.equals(f_TimeTableData3
										.get(number3).getClassOfGrade()
										.getTeachers().getName(),
										f_TimeTableData3.get(number)
												.getClassOfGrade()
												.getTeachers().getName())) {

									if (DEBUG) {
										System.out.println("同じ先生");
									}
									return true;
								}

								// 同じ教室のとき
								if (Objects.equals(f_TimeTableData3
										.get(number3).getClassRoom(),
										f_TimeTableData3.get(number)
												.getClassRoom())) {

									if (DEBUG) {
										System.out.println("教室と重複");
									}
									return true;
								}

								// コース・クラスが重複するとき
								if (checkDuplicationCouseOrClass(
										f_TimeTableData3.get(number3)
												.getClassOfGrade()
												.getCourseOrClass(),
										f_TimeTableData3.get(number)
												.getClassOfGrade()
												.getCourseOrClass()) == true) {

									if (DEBUG) {
										System.out.println("コース・クラスと重複");
									}
									return true;
								}
							}
						}
					}

					// 違う学年のとき
					else {

						for (int addPeriod = 0; addPeriod < f_TimeTableData3
								.get(number3).getClassOfGrade().getNumber(); addPeriod++) {

							// 同じ限目のとき
							if (f_TimeTableData3.get(number3).getPeriod()
									+ addPeriod == f_TimeTableData3.get(number)
									.getPeriod()) {

								// 同じ先生のとき
								if (Objects.equals(f_TimeTableData3
										.get(number3).getClassOfGrade()
										.getTeachers().getName(),
										f_TimeTableData3.get(number)
												.getClassOfGrade()
												.getTeachers().getName())) {

									if (DEBUG) {
										System.out.println("先生と重複(違う学年)");
									}
									return true;
								}

								// 同じ教室のとき
								if (Objects.equals(f_TimeTableData3
										.get(number3).getClassRoom(),
										f_TimeTableData3.get(number)
												.getClassRoom())) {
									if (DEBUG) {
										System.out.println("教室と重複(違う学年)");
									}
									return true;
								}
							}
						}
					}
				}
			}
		}

		return false;
	}

	// /*
	// * コース・クラスと重複しているかのチェック
	// *
	// * @param course_or_class1
	// *
	// * @param course_or_class2
	// *
	// * @return true 重複している
	// *
	// * @return false 重複していない
	// */
	// private boolean checkDuplicationTeacher(String course_or_class1,
	// String course_or_class2) {
	//
	// // 同じ先生のとき
	// if (Objects.equals(course_or_class1, course_or_class2)) {
	//
	// if (DEBUG) {
	// System.out.println("同じ先生");
	// }
	//
	// return true;
	// }
	//
	// // 同じクラスのとき
	// if (Objects.equals("各先生", course_or_class1)) {
	//
	// if (DEBUG) {
	// System.out.println("各先生と重複");
	// }
	//
	// return true;
	// }
	//
	// return false;
	// }

	/*
	 * コース・クラスと重複しているかのチェック
	 * 
	 * @param course_or_class1
	 * 
	 * @param course_or_class2
	 * 
	 * @return true 重複している
	 * 
	 * @return false 重複していない
	 */
	private boolean checkDuplicationCouseOrClass(String course_or_class1,
			String course_or_class2) {

		// 同じクラスのとき
		if (Objects.equals(course_or_class1, course_or_class2)) {

			if (DEBUG) {
				System.out.println("同じクラス");
			}

			return true;
		}

		// abクラスと被るとき
		if (Objects.equals("ab", course_or_class1)) {

			// bcクラスのとき
			if (Objects.equals("bc", course_or_class2)) {
				return false;
			}

			// cクラスのとき
			if (Objects.equals("c", course_or_class2)) {
				return false;
			}

			if (DEBUG) {
				System.out.println("abと重複");
			}
			return true;
		}

		// bcクラスと被るとき
		if (Objects.equals("bc", course_or_class1)) {

			// aクラスのとき
			if (Objects.equals("a", course_or_class2)) {
				return false;
			}

			// abクラスのとき
			if (Objects.equals("ab", course_or_class2)) {
				return false;
			}

			if (DEBUG) {
				System.out.println("bcと重複");
			}
			return true;
		}

		// 奇数クラスと被るとき
		if (Objects.equals("奇数", course_or_class1)) {

			// 偶数クラスのとき
			if (Objects.equals("偶数", course_or_class2)) {

				return false;
			}

			if (DEBUG) {
				System.out.println("奇数と重複");
			}
			return true;
		}

		// 偶数クラスと被るとき
		if (Objects.equals("偶数", course_or_class1)) {

			// 奇数クラスのとき
			if (Objects.equals("奇数", course_or_class2)) {

				return false;
			}

			if (DEBUG) {
				System.out.println("偶数と重複");
			}
			return true;
		}

		// aクラスと被るとき
		if (Objects.equals("a", course_or_class1)) {

			// bクラスのとき
			if (Objects.equals("b", course_or_class2)) {
				return false;
			}

			// cクラスのとき
			if (Objects.equals("c", course_or_class2)) {
				return false;
			}

			// bcクラスのとき
			if (Objects.equals("bc", course_or_class2)) {
				return false;
			}

			if (DEBUG) {
				System.out.println("aと重複");
			}
			return true;
		}

		// bクラスと被るとき
		if (Objects.equals("b", course_or_class1)) {

			// aクラスのとき
			if (Objects.equals("a", course_or_class2)) {
				return false;
			}

			// cクラスのとき
			if (Objects.equals("c", course_or_class2)) {
				return false;
			}

			if (DEBUG) {
				System.out.println("bと重複");
			}

			return true;

		}

		// cクラスと被るとき
		if (Objects.equals("c", course_or_class1)) {

			// aクラスのとき
			if (Objects.equals("a", course_or_class2)) {
				return false;
			}

			// bクラスのとき
			if (Objects.equals("b", course_or_class2)) {
				return false;
			}

			// abクラスのとき
			if (Objects.equals("ab", course_or_class2)) {
				return false;
			}

			if (DEBUG) {
				System.out.println("cと重複");
			}
			return true;
		}

		// エレ情(奇数)クラスと被るとき
		if (Objects.equals("エレ情(奇数)", course_or_class1)) {

			// エネ環クラスのとき
			if (Objects.equals("エネ環", course_or_class2)) {
				return false;
			}

			// エレ情(偶数)クラスのとき
			if (Objects.equals("エレ情(偶数)", course_or_class2)) {
				return false;
			}

			if (DEBUG) {
				System.out.println("エレ情(奇数)と重複");
			}
			return true;
		}

		// エレ情(偶数)クラスと被るとき
		if (Objects.equals("エレ情(偶数)", course_or_class1)) {

			// エネ環クラスのとき
			if (Objects.equals("エネ環", course_or_class2)) {
				return false;
			}

			// エレ情(奇数)クラスのとき
			if (Objects.equals("エレ情(奇数)", course_or_class2)) {
				return false;
			}

			if (DEBUG) {
				System.out.println("エレ情(偶数)と重複");
			}
			return true;
		}

		// エレ情クラスと被るとき
		if (Objects.equals("エレ情", course_or_class1)) {

			// エネ環クラスのとき
			if (Objects.equals("エネ環", course_or_class2)) {
				return false;
			}

			if (DEBUG) {
				System.out.println("エレ情と重複");
			}
			return true;
		}

		// エネ環クラスと被るとき
		if (Objects.equals("エネ環", course_or_class1)) {

			// エレ情クラスのとき
			if (Objects.equals("エレ情", course_or_class2)) {
				return false;
			}

			// エレ情(奇数)クラスのとき
			if (Objects.equals("エレ情(奇数)", course_or_class2)) {
				return false;
			}

			// エレ情(偶数)クラスのとき
			else if (Objects.equals("エレ情(偶数)", course_or_class2)) {
				return false;
			}
			if (DEBUG) {
				System.out.println("エネ環と重複");
			}
			return true;
		}

		// 共通クラスと被るとき
		if (Objects.equals("共通", course_or_class1)) {

			if (DEBUG) {
				System.out.println("共通と重複");
			}
			return true;

		}

		// 再履修クラスと被るとき
		if (Objects.equals("再履修", course_or_class1)) {
			if (DEBUG) {
				System.out.println("再履修と重複");
			}

			return true;
		}

		return false;
	}

	/*
	 * プログラムを実行する
	 */
	private void executeProg() {
		int count = 0;
		PROG_COUNT++;
		super.startProg();// プログラム実行開始

		do {
			System.out.println("回数：" + (count + 1) + "回目");

			// プログラムを終えたとき
			if (isFinishedProg()) {
				break;
			}
			count++;
		} while (true);
		super.finishProg();// プログラム実行終了
	}

	/*
	 * プログラムを終了したか
	 * 
	 * @return true:終了
	 */
	private boolean isFinishedProg() {
		super.readTeacherFile();// 先生のファイルを読み込む
		readFacultyFile();// 担当者が決まったファイルを読み込む(3次のファイル)
		readRoomDayPeriodFile();// 教室と何曜日と何限目が決まったファイルを読み込む(1次と2次のファイル)
		makeTimeTable3();// 3次の時間割を作成

		if (DEBUG) {
			indicateData();// データの表示
			// result();// 結果の表示
		}
		return true;
	}

	/*
	 * 終了
	 */
	public void finish() {
		writeRoomAndDayAndPeriodFile();// 教室と何曜日と何限目が決まったファイルを書き込む
		super.finish();
	}

	/*
	 * 実行する処理
	 * 
	 * @return 0:終了
	 */
	public int exe() {

		for (;;) {
			switch (super.menu()) {
			case 1:
				super.instruction();// 説明
				break;
			case 2:
				executeProg();// プログラムを実行
				break;
			case 3:
				finish();// 終了
				return 0;
			}
		}
	}

	// --------------------------------//
	// ----------ファイル関係----------//
	// --------------------------------//
	/*
	 * 教室と何曜日と何限目が決まったファイルを書き込む
	 */
	public void writeRoomAndDayAndPeriodFile() {
		// writeRoomAndDayAndPeriodFile1();// 1次のファイル(教室と何曜日と何限目が決まったファイル)を書き込む
		// writeRoomAndDayAndPeriodFile2();// 2次のファイル(教室と何曜日と何限目が決まったファイル)を書き込む
		writeRoomAndDayAndPeriodFile3();// 3次のファイル(教室と何曜日と何限目が決まったファイル)を書き込む
	}

	/*
	 * 1次のファイル(教室と何曜日と何限目が決まったデータ)を書き込む
	 */
	private void writeRoomAndDayAndPeriodFile1() {

		// PrintWriter output;
		// output = FileIO.writeFile(FILE1_NAME, false);
		//
		// System.out.println("担当者が決まった3次のファイル" + FACULTY3_NAME + "に書き込みます。");
		//
		// for(int number=0;number<f_TimeTableData3.size();number++){
		// output.print(f_TimeTableData3.get(number).getDayOfWeeks() + ",");//
		// 曜日
		// output.print(f_TimeTableData3.get(number).getDayOfWeeks() + ",");// 限
		// output.print(f_TimeTableData3.get(number).getDayOfWeeks() + ",");//
		// コマ数
		// output.print(f_TimeTableData3.get(number).getDayOfWeeks() + ",");//
		// 学年
		// output.print(f_TimeTableData3.get(number).getDayOfWeeks() + ",");//
		// 前期後期
		// output.print(f_TimeTableData3.get(number).getDayOfWeeks() + ",");//
		// 科目名
		// output.print(f_TimeTableData3.get(number).getDayOfWeeks() + ",");//
		// 担当教員
		// output.print(f_TimeTableData3.get(number).getDayOfWeeks() + ",");//
		// 教室
		// output.println();
		// }
	}

	/*
	 * 2次のファイル(教室と何曜日と何限目が決まったデータ)を書き込む
	 */
	private void writeRoomAndDayAndPeriodFile2() {

		// PrintWriter output;
		// output = FileIO.writeFile(FILE2_NAME, false);
		//
		// System.out.println("担当者が決まった3次のファイル" + FACULTY3_NAME + "に書き込みます。");
		//
		// for(int number=0;number<f_TimeTableData3.size();number++){
		// output.print(f_TimeTableData3.get(number).getDayOfWeeks() + ",");//
		// 曜日
		// output.print(f_TimeTableData3.get(number).getDayOfWeeks() + ",");// 限
		// output.print(f_TimeTableData3.get(number).getDayOfWeeks() + ",");//
		// コマ数
		// output.print(f_TimeTableData3.get(number).getDayOfWeeks() + ",");//
		// 学年
		// output.print(f_TimeTableData3.get(number).getDayOfWeeks() + ",");//
		// 前期後期
		// output.print(f_TimeTableData3.get(number).getDayOfWeeks() + ",");//
		// 科目名
		// output.print(f_TimeTableData3.get(number).getDayOfWeeks() + ",");//
		// 担当教員
		// output.print(f_TimeTableData3.get(number).getDayOfWeeks() + ",");//
		// 教室
		// output.println();
		// }
	}

	/*
	 * 3次のファイル(教室と何曜日と何限目が決まったデータ)を書き込む
	 */
	private void writeRoomAndDayAndPeriodFile3() {

		PrintWriter output;
		output = FileIO.writeFile(FILE3_NAME, false);

		System.out.println("担当者が決まった3次のファイル" + FILE3_NAME + "に書き込みます。");

		output.print("曜日,");// 曜日
		output.print("限目,");// 限
		output.print("コマ数,");// コマ数
		output.print("学年,");// 学年
		output.print("前期後期,");// 前期後期
		output.print("科目名,");// 科目名
		output.print("担当教員,");// 担当教員
		output.print("教室,");// 教室
		output.println("コース・クラス");// コース・クラス

		for (int number = 0; number < f_TimeTableData3.size(); number++) {
			output.print(f_TimeTableData3.get(number).getDayOfWeek() + ",");// 曜日
			output.print(f_TimeTableData3.get(number).getPeriod() + ",");// 限
			output.print(f_TimeTableData3.get(number).getClassOfGrade()
					.getNumber()
					+ ",");// コマ数
			output.print(f_TimeTableData3.get(number).getClassOfGrade()
					.getGrade()
					+ ",");// 学年
			output.print(f_TimeTableData3.get(number).getClassOfGrade()
					.getPreviousOrLatter()
					+ ",");// 前期後期
			output.print(f_TimeTableData3.get(number).getClassOfGrade()
					.getSubject()
					+ ",");// 科目名
			output.print(f_TimeTableData3.get(number).getClassOfGrade()
					.getTeachers().getName()
					+ ",");// 担当教員
			output.print(f_TimeTableData3.get(number).getClassRoom() + ",");// 教室
			output.println(f_TimeTableData3.get(number).getClassOfGrade()
					.getCourseOrClass());// コース・クラス
		}

		output.close();
		System.out.println(FILE3_NAME + "へ書き込みました。");
	}

	/*
	 * 教室と何曜日と何限目が決まったファイルを読み込む
	 */
	private void readRoomDayPeriodFile() {
		readRoomDayPeriodFile1();// 1次のファイル(教室と何曜日と何限目が決まったファイル)を読み込む
		readRoomDayPeriodFile2();// 2次のファイル(教室と何曜日と何限目が決まったファイル)を読み込む
		// readRoomDayPeriodFile3();// 3次のファイル(教室と何曜日と何限目が決まったファイル)を読み込む
	}

	/*
	 * 1次のファイル(教室と何曜日と何限目が決まったファイル)を読み込む
	 */
	private void readRoomDayPeriodFile1() {

		String[] strData = new String[ORDER1_DATA];

		BufferedReader input = FileIO.readFile(FILE1_NAME);

		try {
			String line = new String();

			// 一番上から一番下の行まで読み込む
			for (int cols = 0; cols < ORDER1_COLS; cols++) {

				// 読み込んだ1行が空白でないとき
				if ((line = input.readLine()) != null) {

					// 上から1行以上のとき
					if (1 <= cols) {
						strData = line.split(",");

						// 曜日が空白でないとき
						if (strData[0] != null) {
							TimeTable timeTableData = new TimeTable();
							timeTableData.setDayOfWeek(strData[0]);// 曜日
							timeTableData.setPeriod(Integer
									.parseInt(strData[1]));// 限目
							timeTableData.getClassOfGrade().setNumber(
									Integer.parseInt(strData[2]));// コマ数
							timeTableData.getClassOfGrade().setGrade(
									Integer.parseInt(strData[3]));// 学年
							timeTableData.getClassOfGrade()
									.setPreviousOrLatter(strData[4]);// 前期後期
							timeTableData.getClassOfGrade().setSubject(
									strData[5]);// 科目名
							timeTableData.getClassOfGrade().getTeachers()
									.setName(strData[6]);// 担当教員
							timeTableData.setClassRoom(strData[7]);// 教室
							timeTableData.getClassOfGrade().setCourseOrClass(
									strData[8]);// コース・クラス

							f_TimeTableData1.add(timeTableData);// 1次の時間割の動的配列に追加
						}
					}
				}
			}
			input.close();
		} catch (IOException error_report) {
			System.out.println(error_report);
		}
	}

	/*
	 * 2次のファイル(教室と何曜日と何限目が決まったファイル)を読み込む
	 */
	private void readRoomDayPeriodFile2() {

		String[] strData = new String[ORDER2_DATA];

		BufferedReader input = FileIO.readFile(FILE2_NAME);

		try {
			String line = new String();

			// 一番上から一番下の行まで読み込む
			for (int cols = 0; cols < ORDER2_COLS; cols++) {

				// 読み込んだ1行が空白でないとき
				if ((line = input.readLine()) != null) {

					// 上から1行以上のとき
					if (1 <= cols) {
						strData = line.split(",");

						TimeTable timeTableData = new TimeTable();
						timeTableData.setDayOfWeek(strData[0]);// 曜日
						timeTableData.setPeriod(Integer.parseInt(strData[1]));// 限目
						timeTableData.getClassOfGrade().setNumber(
								Integer.parseInt(strData[2]));// コマ数
						timeTableData.getClassOfGrade().setGrade(
								Integer.parseInt(strData[3]));// 学年
						timeTableData.getClassOfGrade().setPreviousOrLatter(
								strData[4]);// 前期後期
						timeTableData.getClassOfGrade().setSubject(strData[5]);// 科目名
						timeTableData.getClassOfGrade().getTeachers()
								.setName(strData[6]);// 担当教員
						timeTableData.setClassRoom(strData[7]);// 教室

						f_TimeTableData2.add(timeTableData);// 2次の時間割の動的配列に追加

					}
				}
			}
			input.close();
		} catch (IOException error_report) {
			System.out.println(error_report);
		}
	}

	// /*
	// * 3次のファイル(教室と何曜日と何限目が決まったファイル)を読み込む
	// */
	// private void readRoomDayPeriodFile3() {
	//
	// String[] strData = new String[ORDER3_DATA];
	//
	// BufferedReader input = FileIO.readFile(FACULTY3_NAME);
	//
	// ClassOfGrade classOfGradeData = new ClassOfGrade();
	//
	// try {
	// String line = new String();
	//
	// // 一番上から一番下の行まで読み込む
	// for (int cols = 0; cols < ORDER3_COLS; cols++) {
	//
	// // 読み込んだ1行が空白でないとき
	// if ((line = input.readLine()) != null) {
	//
	// // 上から1行以上のとき
	// if (1 <= cols) {
	// strData = line.split(",");
	//
	// classOfGradeData
	// .setNumber(Integer.parseInt(strData[0]));// コマ数
	// classOfGradeData.setGrade(Integer.parseInt(strData[1]));// 学年
	// classOfGradeData.setPreviousOrLatter(strData[2]);// 前期後期
	// classOfGradeData.setSubject(strData[3]);// 科目名
	// classOfGradeData.getTeachers.setName(strData[4]);// 担当教員
	//
	// f_ClassOfGradeData3.add(classOfGradeData);// 3次の時間割の動的配列に追加
	// }
	// }
	// }
	// input.close();
	// } catch (IOException error_report) {
	// System.out.println(error_report);
	// }
	// }

	/*
	 * 担当者が決まったファイルを読み込む
	 */
	private void readFacultyFile() {
		// readFacultyFile1();
		// readFacultyFile2();
		readFacultyFile3();// 3次のファイル(担当者決定のファイル)を読み込む
	}

	/*
	 * 1次のファイル(担当者決定のファイル)を読み込む
	 */
	private void readFacultyFile1() {

		String[] strData = new String[ORDER1_DATA];

		BufferedReader input = FileIO.readFile(FACULTY1_NAME);

		try {
			String line = new String();

			// 一番上から一番下の行まで読み込む
			for (int cols = 0; cols < ORDER1_COLS; cols++) {

				// 読み込んだ1行が空白でないとき
				if ((line = input.readLine()) != null) {

					// 上から1行以上のとき
					if (1 <= cols) {
						strData = line.split(",");

						// 曜日が空白でないとき
						if (strData[0] != null) {
							ClassOfGrade classOfGradeData = new ClassOfGrade();
							classOfGradeData.setNumber(Integer
									.parseInt(strData[0]));// コマ数
							classOfGradeData.setGrade(Integer
									.parseInt(strData[1]));// 学年
							classOfGradeData.setPreviousOrLatter(strData[2]);// 前期後期
							classOfGradeData.setSubject(strData[3]);// 科目名
							classOfGradeData.getTeachers().setName(strData[4]);// 担当教員

							f_ClassOfGradeData1.add(classOfGradeData);// 1次の時間割の動的配列に追加
						}
					}
				}
			}
			input.close();
		} catch (IOException error_report) {
			System.out.println(error_report);
		}
	}

	/*
	 * 2次のファイル(担当者決定のファイル)を読み込む
	 */
	private void readFacultyFile2() {

		String[] strData = new String[ORDER2_DATA];

		BufferedReader input = FileIO.readFile(FACULTY2_NAME);

		try {
			String line = new String();

			// 一番上から一番下の行まで読み込む
			for (int cols = 0; cols < ORDER2_COLS; cols++) {

				// 読み込んだ1行が空白でないとき
				if ((line = input.readLine()) != null) {

					// 上から1行以上のとき
					if (1 <= cols) {
						strData = line.split(",");

						ClassOfGrade classOfGradeData = new ClassOfGrade();
						classOfGradeData
								.setNumber(Integer.parseInt(strData[0]));// コマ数
						classOfGradeData.setGrade(Integer.parseInt(strData[1]));// 学年
						classOfGradeData.setPreviousOrLatter(strData[2]);// 前期後期
						classOfGradeData.setSubject(strData[3]);// 科目名
						classOfGradeData.getTeachers().setName(strData[4]);// 担当教員

						f_ClassOfGradeData2.add(classOfGradeData);// 2次の時間割の動的配列に追加

					}
				}
			}
			input.close();
		} catch (IOException error_report) {
			System.out.println(error_report);
		}
	}

	/*
	 * 3次のファイル(担当者決定のファイル)を読み込む
	 */
	private void readFacultyFile3() {

		String[] strData = new String[ORDER3_DATA];

		BufferedReader input = FileIO.readFile(FACULTY3_NAME);

		try {
			String line = new String();

			// 一番上から一番下の行まで読み込む
			for (int cols = 0; cols < ORDER3_COLS; cols++) {

				// 読み込んだ1行が空白でないとき
				if ((line = input.readLine()) != null) {

					// 上から1行以上のとき
					if (1 <= cols) {
						strData = line.split(",");

						ClassOfGrade classOfGradeData = new ClassOfGrade();
						classOfGradeData
								.setNumber(Integer.parseInt(strData[0]));// コマ数
						classOfGradeData.setGrade(Integer.parseInt(strData[1]));// 学年
						classOfGradeData.setPreviousOrLatter(strData[2]);// 前期後期
						classOfGradeData.setSubject(strData[3]);// 科目名
						classOfGradeData.getTeachers().setName(strData[4]);// 担当教員
						classOfGradeData.setCourseOrClass(strData[5]);// コース・クラス
						f_ClassOfGradeData3.add(classOfGradeData);// 3次の学年ごとの授業の動的配列に追加
					}
				}
			}
			input.close();
		} catch (IOException error_report) {
			System.out.println(error_report);
		}
	}
}
