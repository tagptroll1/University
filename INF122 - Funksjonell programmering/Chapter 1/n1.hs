-- Implement the product function
product' [] = 1
product' [x] = x
product' (x:xs) = x * product' xs

-- Flip the sort to reverse order (or just use reverse)
qsort [] = []
qsort (x:xs) = qsort larger ++ [x] ++ qsort smaller
  where 
    smaller = [a | a <- xs, a < x]
    larger = [b | b <- xs, b > x]

-- Fix 2 syntax errors (' => ` & N => n)
n = a `div` length xs
    where
        a = 10
        xs = [1,2,3,4,5]

-- Implement last
last' [x] = x
last' (x:xs) = last' xs

-- Implement 2 different approaches to `init`
init1 (x:xs) = take (length xs) (x : xs)
init2 xs = reverse (tail (reverse xs))
