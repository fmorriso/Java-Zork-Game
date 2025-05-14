import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String name;
    private boolean isAlive;
    private List<GameArtifact> artifacts;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Player{");
        sb.append("name='").append(name).append('\'');
        sb.append(", isAlive=").append(isAlive);
        sb.append(", artifacts=").append(artifacts);
        sb.append('}');
        return sb.toString();
    }

    public Player(String name) {
        this.name = name;
        this.isAlive = true;
        artifacts = new ArrayList<GameArtifact>();
    }

    public void addArtifact(GameArtifact artifact) {
        artifacts.add(artifact);
    }

    public void removeArtifact(GameArtifact artifact) {
        artifacts.remove(artifact);
    }

    public void clearArtifacts() {
        artifacts = new ArrayList<GameArtifact>();
    }

    public boolean canFight() {
        return artifacts.contains(GameArtifact.SWORD);
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }
}
