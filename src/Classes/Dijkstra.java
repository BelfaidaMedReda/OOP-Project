package Classes;
import java.util.*;

public class Dijkstra {
    private Carte carte;

    public Dijkstra(Carte carte) {
        this.carte = carte;
    }

    public List<Case> recherchePlusCourtChemin(Robot robot, Case start, Case destination) {
        Map<Case, Double> minTime = new HashMap<>();
        Map<Case, Case> previousCase = new HashMap<>();
        PriorityQueue<PathNode> queue = new PriorityQueue<>(Comparator.comparingDouble(node -> node.time));

        minTime.put(start, 0.0);
        queue.add(new PathNode(start, 0.0));

        while (!queue.isEmpty()) {
            PathNode current = queue.poll();
            Case currentCase = current.caseNode;

            if (currentCase.equals(destination)) break;

            for (Case voisin : carte.getVoisins(currentCase)) {
                if (!robot.canTraverse(voisin)) continue;

                double timeToNeighbor = minTime.get(currentCase) + robot.getTempsTraverse(voisin);

                if (timeToNeighbor < minTime.getOrDefault(voisin, Double.POSITIVE_INFINITY)) {
                    minTime.put(voisin, timeToNeighbor);
                    previousCase.put(voisin, currentCase);
                    queue.add(new PathNode(voisin, timeToNeighbor));
                }
            }
        }

        return reconstructPath(previousCase, start, destination);
    }

    private List<Case> reconstructPath(Map<Case, Case> previousCase, Case start, Case destination) {
        LinkedList<Case> path = new LinkedList<>();
        Case at = destination;
        while (at !=null){
            path.addFirst(at);
            at = previousCase.get(at);
        }
        return path.isEmpty() || !path.getFirst().equals(start) ? Collections.emptyList() : path;
    }

    private static class PathNode {
        Case caseNode;
        double time;

        PathNode(Case caseNode, double time) {
            this.caseNode = caseNode;
            this.time = time;
        }
    }
}


