import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String name;
    private boolean isAlive;
    private Room previousRoom;
    private List<GameArtifact> artifacts;

    public Player(String name) {
        this.name = name;
        this.isAlive = true;
        artifacts = new ArrayList<GameArtifact>();
    }

    public Room getPreviousRoom() {return previousRoom;}
    public void setPreviousRoom(Room previousRoom) {this.previousRoom = previousRoom;}

    public void addArtifact(GameArtifact artifact) {
        artifacts.add(artifact);
    }

    public void removeArtifact(GameArtifact artifact) {
        artifacts.remove(artifact);
    }

    public void clearArtifacts() {
        artifacts = new ArrayList<GameArtifact>();
    }

    public boolean canFightRegularMonster() {
        return artifacts.contains(GameArtifact.SWORD);
    }

    public boolean canFightBossMonster() {
        return canFightRegularMonster() && artifacts.contains(GameArtifact.MAGICSTONES);
    }

    public boolean isAlive() {return isAlive;}
    public void setAlive(boolean alive) {isAlive = alive;}

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Player{");
        sb.append("name='").append(name).append('\'');
        sb.append(", isAlive=").append(isAlive);
        sb.append(", artifacts=").append(artifacts);
        if (null != previousRoom)
            sb.append("\n\t    previous room=").append(previousRoom);
        sb.append('}');
        return sb.toString();
    }

    public boolean hasGameArtifact(GameArtifact gameArtifact) {
        return artifacts.contains(gameArtifact);
    }
}
