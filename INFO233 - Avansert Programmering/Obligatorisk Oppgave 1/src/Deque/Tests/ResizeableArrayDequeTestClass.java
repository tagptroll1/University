//package Deque.Tests;
//
//import Deque.ArrayDeque;
//import Deque.ResizeableArrayDeque;
//
//
//public class ResizeableArrayDequeTestClass extends ArrayDequeTestClass {
//
//    public ResizeableArrayDequeTestClass() {
//        deque = new ResizeableArrayDeque<>(6);
//    }
//
//    /**
//     * @return returns the deque to allow for custom calling of deque functions
//     */
//    @SuppressWarnings("unchecked")
//    public ResizeableArrayDeque<String> getDeque() {
//        return (ResizeableArrayDeque) deque;
//    }
//
//    @Override
//    protected ArrayDeque<String> addToTop(String[] elements, ArrayDeque<String> deque){
//        int elementIndex = 0;
//        for (String ele : elements){
//
//            deque.addFirst(ele);
//
//            System.out.println("Adding: " + elementIndex + " arrayIndex to " + deque.getTopIndex() +
//                        ". head-index, element: " + deque.peekFirst());
//
//            elementIndex++;
//            displayEntireArray();
//        }
//        System.out.println("Current Head index is " + deque.getTopIndex());
//        return deque;
//    }
//
//    @Override
//    protected ArrayDeque<String> addToBot(String[] elements, ArrayDeque<String> deque){
//        int elementIndex = 0;
//        for (String ele : elements){
//            deque.addLast(ele);
//
//            System.out.println("Adding: " + elementIndex + " arrayIndex to " + deque.getBotIndex() +
//                        ". tail-index, element: " + deque.peekLast());
//
//            elementIndex++;
//            displayEntireArray();
//        }
//        System.out.println("Current Tail index is " + deque.getBotIndex());
//        return deque;
//    }
//}
