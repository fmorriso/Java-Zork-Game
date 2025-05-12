import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private List<GameArtifact> artifacts;
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


}
