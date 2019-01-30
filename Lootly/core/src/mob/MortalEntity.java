package mob;

public interface MortalEntity {
	int getHP();
	void setHP(int hp);
	int getMaxHP();
	void setMaxHP(int maxHp);
	int getMP();
	void setMP(int mp);
	int getMaxMP();
	void setMaxMP(int maxMp);
	boolean isDead();
}
