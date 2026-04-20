1. What is the root node in this program?
= The root node is the only node without a parent (there can only be one root node). In this program, it is Magic Items Shop.

2. Which nodes are leaf nodes?
= Leaf nodes are the node without a child (there can be many leaf nodes). In this program, it is the basic items. If in the program, a node doesn't have any method ".addChild", it is a leaf node. For example: 
- Expert Gloves
- Book of Sages
- Magic Necklace
- Power Crystal
- Mystery Codex
- Knife
- Vitality Crystal
- Hero's Ring"
- Lantern of Hope
- Flower of Hope
- Leather Jerkin

3. Why is children stored as a List<ItemNode> instead of a single variable?
= If we are using a single variable in this program, it will not be adding up to the logic of the game MLBB. In this case, we want the item can have one to many relationships with the material. Just like Enchanted Talisman needs 3 material (tome of evil, magic necklace and magic wand). So to make it happens, we need list to store the materials as many as we need, and also the materials in list can be dupclicated (e.g Sky Piercer needs two expert gloves).

4. What is the difference between a linear structure and a tree structure in this example?
= In this example, a linear structure is like an array. It moves in sequential order (0,1,2 ....). Other than that, a tree structure is branching to many datas in the same level. In our implementation, it meant that the items can be branched to some materials in the same level, a visual example for a tree structure:
[Root] Magic Items Shop
 │
 ├── Enchanted Talisman (Final Item)
 │    ├── Tome of Evil
 │    │    ├── Magic Necklace
 │    │    └── Book of Sages
 │    ├── Magic Necklace (Langsung dari mentahan)
 │    └── Magic Wand
 │         └── Mystery Codex
 A visual example for a linear structure:
 Magic Items Shop = [Enchanted Talisman, Tome of Evil, Magic Necklace, Book of Sages...]
 This is absolutely wrong because enchanted talisman is not in one level with Tome of Evil, they should be on different level.

 5. How does recursion help when working with trees?
= Actually structure tree already implements the concept of recursion, because every paren tree will be divided into sub trees. The role of recursion is when we write a bunch of codes to be implemented on all the nodes, we just need to write one time than use the recursion to do all the sama things on every node. It also uses the concept of backtracking to not take much of the memory. Every time a function calls itself, Java automatically pauses the current function and saves its exact state, when it got on the end, the function will return to the previous node. This is called backtracking. The example in the code is:
static void printAllBuildPaths(ItemNode node, List<String> path) {
        if (node == null) return; (base case)

        path.add(node.name); // (to save the moves before)

        if (node.children.isEmpty()) {
            System.out.println(String.join(" -> ", path));
        } else {
            for (ItemNode child : node.children) { // (the recursion step)
                printAllBuildPaths(child, path);
            }
        }

        path.remove(path.size() - 1); //(to erase the wrong path/backtracking)
    }

6. What path is printed when searching for Corrosion Scythe?
= In my program, I only made the magic class + immortal, so there isn't any Corrosion Scythe. But I can give example from another item. The output (for Ice Queen Wand):
<<< COMPONENT PATHS >>>
Ice Queen Wand -> Mystic Container -> Mystery Codex
Ice Queen Wand -> Exotic Veil -> Mystery Codex
Ice Queen Wand -> Magic Wand -> Mystery Codex