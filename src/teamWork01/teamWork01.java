package teamWork01;

import java.io.FileInputStream;
import java.util.Random;
import java.util.Scanner;

import javazoom.jl.player.Player;

public class teamWork01 {

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		final int SIZE =7;
		final String RESET  = "\u001B[0m";
		final String RED    = "\u001B[31m";
		final String GREEN  = "\u001B[32m";
		final String YELLOW = "\u001B[33m";
		final String BLUE   = "\u001B[95m";
		final String CYAN   = "\u001B[36m";
		

		// hero
		int hpPlayer = 150;
		int hpMax = 150;
		int dmg = 20;
		int gold = 80;

		// items
		String[] ItemsName = { "Hp potion [S]", "Hp potion [M]", "Hp potion [L]" };
		int[] ItemsNumber = { 5, 2, 1 };
		int[] HealRate = { 35, 55, 100 };

		// map
		int[][] Map = {
				{ 0, 2, 2, 0, 3, 3, 3 },
	            { 0, 3, 2, 0, 2, 3, 3 },
	            { 3, 0, 2, 4, 0, 2, 3 },
	            { 0, 2, 3, 2, 2, 0, 2 },
	            { 3, 0, 2, 0, 3, 2, 0 },
	            { 2, 0, 0, 3, 0, 2, 2 },
	            { 3, 2, 3, 2, 3, 2, 2 }
		};

		int playerX = 0, playerY = 0;
		int monsterX = 2, monsterY = 3;

//		int[][] passed = new int[5][5];
		int[][] passed = new int[SIZE][SIZE];

		// monsterList
		int[] MonsterHp = { 35, 60, 120 };
		int[] MonsterDmg = { 12, 18, 30 };
		int[] MonsterGold = { 30, 50, 80 };

		// ==================== MAIN LOOP ====================
		while (hpPlayer > 0 && !(playerX == monsterX && playerY == monsterY)) {

			// --- draw map ---
			System.out.println("\n===============================");
			for (int i = 0; i < SIZE; i++) {
				for (int j = 0; j < SIZE; j++) {
					if (i == playerX && j == playerY)
						System.out.print(CYAN+"‍ 🥷 "+RESET);
					else if (i == monsterX && j == monsterY)
						System.out.print(RED+" 🚩 "+RESET);
					else if (Map[i][j] == 0)
						System.out.print(YELLOW+" 🐍 "+RESET);
					else if (Map[i][j] == 2)
						System.out.print(" ⛩️ ");
					else if (Map[i][j] == 3)
						System.out.print(BLUE+" ☠️ "+RESET);
					else if (Map[i][j] == 4)
						System.out.print(" 🏆 ");
				}
				System.out.println();
			}
			System.out.println("===============================");
			System.out.println(CYAN+"📍 ‍🥷 Location: (" + playerX + "," + playerY + ")"+RESET);
			System.out.println(RED+"♥️ HP: " + hpPlayer + "/" + hpMax + " | ⚔ ATK: " + dmg + " | 🪙 Gold: "+ gold+RESET );
			System.out.println("🚩 Goal: (" + monsterX + "," + monsterY + ")");

			// --- move menu ---
			System.out.println("========================================");
			System.out.println("\n1. ▲ | 2. ▼ | 3.  —▶ | 4. ◀— | 0. Exit");
			System.out.print("どうする?: ");
			int move = sc.nextInt();
			System.out.println("========================================");
			sc.nextLine();

			if (move == 0) {
				System.out.println("👋 bye bye");
				sc.close();
				return;
			}

			int newX = playerX, newY = playerY;
			if (move ==  1)
				newX--;
			else if (move == 2)
				newX++;
			else if (move == 4)
				newY--;
			else if (move == 3)
				newY++;
			else {
				System.out.println("⚠️ Invalid input!");
				continue;
			}

			if (newX < 0 || newX >= SIZE || newY < 0 || newY >= SIZE) { // 
				System.out.println("⚠️ Out of map!");
				continue;
			}

			playerX = newX;
			playerY = newY;

			int type0 = Map[playerX][playerY];

			// ---- 神社 ----
			if (type0 == 2) {
			    int heal = 5 + random.nextInt(8); // hồi 15-25 HP
			    hpPlayer = Math.min(hpPlayer + heal, hpMax); // không vượt hpMax
			    System.out.println(YELLOW+"\n⛩️お祈り! ブレッシング +" + heal + " HP!"+RESET);
			    System.out.println("♥️ HP: " + hpPlayer + "/" + hpMax);
			    System.out.println("Enter to continue...");
			    sc.nextLine();
			}

			// ---- trap ----
			else if (type0 == 3) {
				int trapDmg = 8 + random.nextInt(30);
				hpPlayer -= trapDmg;
				if (hpPlayer < 0)
					hpPlayer = 0;
				System.out.println(BLUE+"\n☠️ 危ない!!! -" + trapDmg + " HP!"+RESET);
				System.out.println("Enter to continue...");
				sc.nextLine();
				if (hpPlayer <= 0) {
					System.out.println("\nYou Die!!! GAME OVER!!!");
					break;
				}
			}

			// ---- goal ----
			else if (type0 == 4) {
				System.out.println("\n🎆🎆🎆 おめでとうございます！！！ 🎆🎆🎆");
				break;
			}

			// ---- monster ----
			else if (type0 == 0) {
				if (passed[playerX][playerY] == 1) {
					System.out.println("Already cleared.");
					System.out.println("Enter to continue...");
					sc.nextLine();
					continue;
				}

				int indexMonster = random.nextInt(MonsterHp.length);
				int hpMonster = MonsterHp[indexMonster];
				int dmgMonster = MonsterDmg[indexMonster];

				System.out.println("Monster!!!");
				play4();
				play5();
				System.out.println("Monster: HP " + hpMonster + " | ATK " + dmgMonster);

				// ---- battle loop ----
				while (hpPlayer > 0 && hpMonster > 0) {
					System.out.println("\nHero: " + hpPlayer + "/" + hpMax + " | Monster: " + hpMonster);
					System.out.println("1. Attack | 2. Use potion | 3. Run (Game Over)");
					System.out.print("You will.... ");

					int chose = sc.nextInt();
					sc.nextLine();

					if (chose == 1) {
						play3();
						// player attacks
						int damage = dmg + random.nextInt(7) - 3;
						if (damage < 5)
							damage = 5;
						hpMonster -= damage;
						System.out.println("⚔️ You dealt " + damage + " dmg! Monster HP: " + Math.max(0, hpMonster));
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						if (hpMonster <= 0) {
							play1();
							System.out.println("🏆 You win! Got: " + MonsterGold[indexMonster] + " gold");
							gold += MonsterGold[indexMonster];
							passed[playerX][playerY] = 1;
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							break;
						}

						// monster counter-attacks    
						int damaged = dmgMonster + random.nextInt(7) - 3;
						if (damaged < 2)
							damaged = 2;
						hpPlayer -= damaged;
						if (hpPlayer < 0)
							hpPlayer = 0;
						System.out.println("💢 Monster attacked you for " + damaged + " dmg!");
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					} else if (chose == 2) {
						// use item                   
						System.out.println("\n--- BAG ---");
						boolean hasItem = false;
						for (int i = 0; i < ItemsName.length; i++) {
							if (ItemsNumber[i] > 0) {
								System.out.println((i + 1) + ". " + ItemsName[i]
										+ " x" + ItemsNumber[i] + " (heal " + HealRate[i] + " HP)");
								hasItem = true;
							}
						}
						if (!hasItem) {
							System.out.println("❌ No items left!");
							continue;
						}

						System.out.print("Choose (0 to cancel): ");
						int chonVp = sc.nextInt();
						sc.nextLine();

						if (chonVp == 0)
							continue;
						chonVp--;

						if (chonVp >= 0 && chonVp < ItemsName.length && ItemsNumber[chonVp] > 0) {
							ItemsNumber[chonVp]--;
							hpPlayer = Math.min(hpPlayer + HealRate[chonVp], hpMax);
							System.out.println("✨ Used " + ItemsName[chonVp] + "! HP: " + hpPlayer + "/" + hpMax);
						} else {
							System.out.println("❌ Invalid choice!");
						}

					} else if (chose == 3) {
						play2();
						System.out.println("🏃 You ran away! GAME OVER");
						sc.close();
						return;
					} else {
						System.out.println("⚠️ Invalid input!");
					}
				} // end battle loop

				if (hpPlayer <= 0) {
					play2();
					System.out.println("\n💀 YOU DIED! GAME OVER 💀");
					break;
				}
			}
		}

		// ==================== END ====================
		if (hpPlayer > 0 && playerX == monsterX && playerY == monsterY) {
			play1();
			System.out.println("\n✨✨✨ VICTORY! ✨✨✨");
			System.out.println("🪙 Gold: " + gold);
		} else if (hpPlayer <= 0) {
			play2();
			System.out.println("\n💀 GAME OVER 💀");
		}

		sc.close();
	}
	
	public static void play1() throws Exception { //cai nay dc cai ben tren doc

		FileInputStream fis = new FileInputStream("レベルアップ.mp3");
		Player player = new Player(fis);
		player.play();
	}

	public static void play2() throws Exception { //cai nay dc cai ben tren doc

		FileInputStream fis = new FileInputStream("ボヨヨーン.mp3");
		Player player = new Player(fis);
		player.play();
	}
	public static void play3() throws Exception { //cai nay dc cai ben tren doc

		FileInputStream fis = new FileInputStream("剣で斬る2.mp3");
		Player player = new Player(fis);
		player.play();
	}
	public static void play4() throws Exception { //cai nay dc cai ben tren doc

		FileInputStream fis = new FileInputStream("ゴブリンの鳴き声3.mp3");
		Player player = new Player(fis);
		player.play();
	}
	public static void play5() throws Exception { //cai nay dc cai ben tren doc

		FileInputStream fis = new FileInputStream("「さあ、いくぞ！」.mp3");
		Player player = new Player(fis);
		player.play();
	}
	public static void play6() throws Exception { //cai nay dc cai ben tren doc

		FileInputStream fis = new FileInputStream("「これより10分間の休憩となります」.mp3");
		Player player = new Player(fis);
		player.play();
	}
}