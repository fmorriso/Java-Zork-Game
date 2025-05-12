import java.util.Random;

public enum GameArtifact {
   SWORD,
   MONSTER,
   MAGICSTONES,
   STAIRS;

   private static final Random PRNG = new Random();

   public static GameArtifact getRandomRandomGameArtifact() {
      GameArtifact[] artifacts = GameArtifact.values();
      return artifacts[PRNG.nextInt(artifacts.length)];
   }
}
