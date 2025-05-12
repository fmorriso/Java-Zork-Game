import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private List<GameArtifact> artifacts;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Player{");
        sb.append("name='").append(name).append('\'');
        sb.append(", artifacts=").append(artifacts);
        sb.append('}');
        return sb.toString();
    }

    public Player(String name) {
        this.name = name;
        artifacts = new ArrayList<GameArtifact>();
    }

    public void addArtifact(GameArtifact artifact) {
        artifacts.add(artifact);
    }

    public void removeArtifact(GameArtifact artifact) {
        artifacts.remove(artifact);
    }


    public void clearArifacts() {
        artifacts = new ArrayList<GameArtifact>();
    }
}
