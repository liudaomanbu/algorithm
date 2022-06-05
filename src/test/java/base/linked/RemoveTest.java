package base.linked;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.RepeatedTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author caotc
 * @date 2022-04-11
 * @since 1.0.0
 */
@Slf4j
public class RemoveTest {

    <T> List<T> getOriginOrderList(Node<T> head) {
        List<T> ans = new ArrayList<>();
        while (head != null) {
            ans.add(head.value);
            head = head.next;
        }
        return ans;
    }

    <T> List<T> getOriginOrderList(DoubleNode<T> head) {
        List<T> ans = new ArrayList<>();
        while (head != null) {
            ans.add(head.value);
            head = head.next;
        }
        return ans;
    }

    @RepeatedTest(1000)
    public void test(){
        Remove remove=new Remove();
        Node<Integer> node = NodeFactory.generateIntegerNode(50, 100);
        List<Integer> list = getOriginOrderList(node);
        if(node!=null){
            Integer removeValue=list.remove((int) (Math.random() * list.size()));
            node = remove.remove(node, removeValue);
        }
        List<Integer> resultList = getOriginOrderList(node);
        if(!list.equals(resultList)){
            log.error("{}", list);
            log.error("{}", resultList);
        }
    }
}
