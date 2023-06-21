package graph;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.nio.dot.DOTExporter;
import org.junit.jupiter.api.Test;

import java.io.StringWriter;

/**
 * @author zhanghanlin
 * @date 2023/6/21
 **/
public class JGraphTest {

    @Test
    void testSimpleGraph() {
        Graph<String,DefaultWeightedEdge> graph = new SimpleGraph<>(DefaultWeightedEdge.class);

        graph.addVertex("v1");
        graph.addVertex("v2");
        graph.addEdge("v1","v2");

        StringWriter writer = new StringWriter();

        DOTExporter<String, DefaultWeightedEdge> exporter = new DOTExporter<>();
        exporter.exportGraph(graph, writer);

        System.out.println(writer);
    }
}
