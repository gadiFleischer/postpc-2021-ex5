
package exercise.android.reemh.todo_items;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TodoItemsHolderImplTest {
  @Test
  public void when_addingTodoItem_then_callingListShouldHaveThisItem(){
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
    assertEquals(0, holderUnderTest.getCurrentItems().size());

    // test
    holderUnderTest.addNewInProgressItem("do shopping");

    // verify
    assertEquals(1, holderUnderTest.getCurrentItems().size());
  }
  @Test
  public void add10Items(){
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
  for (int i = 1;i<11;i++){
    String desc = "item"+i;
    holderUnderTest.addNewInProgressItem(desc);
  }
    assertEquals(10, holderUnderTest.getCurrentItems().size());
  }

  @Test
  public void itemAddedIsNotMarked(){
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
    holderUnderTest.addNewInProgressItem("do shopping");
    assertEquals(false,
            holderUnderTest.getCurrentItems().get(0).isDone);
  }
  @Test
  public void checkCheckBox(){
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
    holderUnderTest.addNewInProgressItem("do shopping");
    assertEquals(false,
            holderUnderTest.getCurrentItems().get(0).isDone);
    holderUnderTest.getCurrentItems().get(0).setCheckBoxDone();
    assertEquals(true,
            holderUnderTest.getCurrentItems().get(0).isDone);
    holderUnderTest.getCurrentItems().get(0).setCheckBoxInProg();
    assertEquals(false,
            holderUnderTest.getCurrentItems().get(0).isDone);
  }
  @Test
  public void checkRemoveButton(){
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
    holderUnderTest.addNewInProgressItem("do shopping");
    assertEquals(1, holderUnderTest.getCurrentItems().size());
    TodoItem item =   holderUnderTest.getCurrentItems().get(0);
    holderUnderTest.deleteItem(item);
    assertEquals(0, holderUnderTest.getCurrentItems().size());
  }
  @Test
  public void checkItemsDescription(){
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
    for (int i = 1;i<11;i++){
      String desc = "item"+i;
      holderUnderTest.addNewInProgressItem(desc);
    }
    for (int i = 0;i<10;i++){
      String desc = "item"+(i+1);
      assertEquals(desc, holderUnderTest.getCurrentItems().get(i).description);
    }
  }
  @Test
  public void checkSortProgBeforeDone(){
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
    holderUnderTest.addNewInProgressItem("1");
    holderUnderTest.addNewInProgressItem("2");
    assertEquals("1", holderUnderTest.getCurrentItems().get(0).description);
    holderUnderTest.markItemDone(holderUnderTest.getCurrentItems().get(0));
    assertEquals("2", holderUnderTest.getCurrentItems().get(0).description);
  }
  @Test
  public void delete9Items(){
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
    for (int i = 1;i<11;i++){
      String desc = "item"+i;
      holderUnderTest.addNewInProgressItem(desc);
    }
    assertEquals(10, holderUnderTest.getCurrentItems().size());
    for (int i = 1;i<11;i++){
      holderUnderTest.deleteItem(holderUnderTest.getCurrentItems().get(0));
      assertEquals(10-i, holderUnderTest.getCurrentItems().size());
    }
  }
  @Test
  public void checkTime(){
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
    holderUnderTest.addNewInProgressItem("something");
    assertNotEquals("", holderUnderTest.getCurrentItems().get(0).createdTime);
  }
  @Test
  public void checkTime2(){
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
    holderUnderTest.addNewInProgressItem("something");
    TodoItem item = holderUnderTest.getCurrentItems().get(0);
    assertNotEquals("", item.createdTime);
    assertEquals("something", item.description);
    assertEquals(false, item.isDone);

  }
}
