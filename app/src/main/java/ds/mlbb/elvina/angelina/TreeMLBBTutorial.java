package ds.mlbb.elvina.angelina;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TreeMLBBTutorial {
    static class ItemNode {
        String name;
        String note;
        List<ItemNode> children;

        ItemNode(String name, String note) {
            this.name = name;
            this.note = note;
            this.children = new ArrayList<>();
        }

        void addChild(ItemNode child) {
                children.add(child);
            }
        }

    static void printTree(ItemNode node, int level) {
        if (node == null) return;

        String indent = " ".repeat(level);
        System.out.println(indent + "- " + node.name + " -> " + node.note);

        for (ItemNode child : node.children) {
            printTree(child, level + 1);
        }
    }

    static void printAllBuildPaths(ItemNode node, List<String> path) {
        if (node == null) return;

        path.add(node.name);

        if (node.children.isEmpty()) {
            System.out.println(String.join(" -> ", path));
        } else {
            for (ItemNode child : node.children) {
                printAllBuildPaths(child, path);
            }
        }

        path.remove(path.size() - 1);
    }

    static int countNodes(ItemNode node) {
        if (node == null) return 0;
        int total = 1;
        for (ItemNode child : node.children) {
            total += countNodes(child);
        }
        return total;
    }

    static int countLeaves(ItemNode node) {
        if (node == null) return 0;
        if (node.children.isEmpty()) return 1;

        int total = 0;
        for (ItemNode child : node.children) {
            total += countLeaves(child);
        }
        return total;
    }

    static int height(ItemNode node) {
        if (node == null) return 0;
        if (node.children.isEmpty()) return 1;

        int maxChildHeight = 0;
        for (ItemNode child : node.children) {
            maxChildHeight = Math.max(maxChildHeight, height(child));
        }
        return 1 + maxChildHeight;
    }

    static boolean findPath(ItemNode node, String target, List<String> path) {
        if (node == null) return false;

        path.add(node.name);

        if (node.name.equalsIgnoreCase(target)) {
            return true;
        }

        for (ItemNode child : node.children) {
            if (findPath(child, target, path)) {
                return true;
            }
        }

        path.remove(path.size() - 1);
        return false;
    }

    static ItemNode findNode(ItemNode node, String targetName) {
        if (node == null) return null;
        
        if (node.name.equalsIgnoreCase(targetName)) {
            return node;
        }
        
        for (ItemNode child : node.children) {
            ItemNode found = findNode(child, targetName);
            if (found != null) {
                return found;
            }
        }
        return null;
    }

    static int countItemOccurrences(ItemNode node, String targetName) {
        if (node == null) return 0;
        int count = 0;
        if (node.name.equalsIgnoreCase(targetName)) {
            count = 1;
        }
        for (ItemNode child : node.children) {
            count += countItemOccurrences(child, targetName);
        }
        return count;
    }

    static void printPathsEndingWith(ItemNode node, List<String> path, String targetNodeName) {
        if (node == null) return;
        path.add(node.name);
        if (node.name.equalsIgnoreCase(targetNodeName)) {
            System.out.println(">> " + String.join(" ~~> ", path));
        }
        for (ItemNode child : node.children) {
            printPathsEndingWith(child, path, targetNodeName);
        }
        path.remove(path.size() - 1);
    }

   static ItemNode buildGlobalMagicTree() {
        // 1. TIER 1 COMPONENTS (BASIC ITEMS)
        ItemNode expertGloves = new ItemNode("Expert Gloves", "Basic Item (+30 Adaptive Attack)");
        ItemNode bookOfSages = new ItemNode("Book of Sages", "Basic Item (+8 Magic Power + 5% Cooldown Reduction)");
        ItemNode magicNecklace = new ItemNode("Magic Necklace", "Basic Item (+2 Mana Regen)");
        ItemNode powerCrystal = new ItemNode("Power Crystal", "Basic Item (+280 mana)");
        ItemNode mysteryCodex = new ItemNode("Mystery Codex", "Basic Item (+15 Magic Power)");
        ItemNode knife = new ItemNode("Knife", "Basic Item From Attack (+10% Attack Speed)");
        ItemNode vitalityCrystal = new ItemNode("Vitality Crystal", "Basic Item from Defense (+230 HP)");
        ItemNode herosRing = new ItemNode("Hero's Ring", "Basic Item from Defense (+150 HP + 5% Cooldown Reduction)");
        ItemNode lanternOfHope = new ItemNode("Lantern of Hope", "Basic Item Just For Floryn (+20 Magic Power +4% Movement Speed +2% Cooldown Reduction)");
        ItemNode flowerOfHope = new ItemNode("Flower of Hope", "Basic Item Just For Floryn (+30% Total Physical Attack +30% Total Magic Power)");
        ItemNode leatherJerkin = new ItemNode("Leather Jerkin", "Basic Item from Defense (+14 Physical Defense)");

        // 2. TIER 2 COMPONENTS (Crafted from Tier 1)
        ItemNode tomeOfEvil = new ItemNode("Tome of Evil", "Tier 2 Item (+15% Magic Power +8% Spell Vamp)");
        tomeOfEvil.addChild(magicNecklace);
        tomeOfEvil.addChild(bookOfSages);

        ItemNode azureBlade = new ItemNode("Azure Blade", "Tier 2 Item (+5% Mana Regen +5% Cooldown Reduction)");
        azureBlade.addChild(magicNecklace);
        azureBlade.addChild(magicNecklace);

        ItemNode exoticVeil = new ItemNode("Exotic Veil", "Tier 2 Item (+30% Magic Power +5% Movement Speed)");
        exoticVeil.addChild(mysteryCodex);

        ItemNode elegantGem = new ItemNode("Elegant Gem", "Tier 2 Item (+300 Hp +380 Mana)");
        elegantGem.addChild(powerCrystal);
        elegantGem.addChild(vitalityCrystal);

        ItemNode swiftCrossbow = new ItemNode("Swift Crossbow", "Tier 2 Item from Attack (+20% Attack Speed)");
        swiftCrossbow.addChild(knife);
        swiftCrossbow.addChild(knife);

        ItemNode mysticContainer = new ItemNode("Mystic Container", "Tier 2 Item (+15 Magic Power +8% Spell Vamp)");
        mysticContainer.addChild(mysteryCodex);

        ItemNode magicWand = new ItemNode("Magic Wand", "Tier 2 Item (+45 Magic Power)");
        magicWand.addChild(mysteryCodex);

        ItemNode aresBelt = new ItemNode("Ares' Belt", "Tier 2 Item from Defense (+600 HP)");
        aresBelt.addChild(vitalityCrystal);

        // 3. TIER 3 COMPONENTS (FINAL ITEMS)
        ItemNode enchantedTalisman = new ItemNode("Enchanted Talisman", "Final Item (Mana Regen & CD Reduction)");
        enchantedTalisman.addChild(tomeOfEvil);
        enchantedTalisman.addChild(magicNecklace);
        enchantedTalisman.addChild(magicWand);

        ItemNode featherOfHeaven = new ItemNode("Feather of Heaven", "Final Item (Magic Basic Attack)");
        featherOfHeaven.addChild(swiftCrossbow);
        featherOfHeaven.addChild(bookOfSages);

        ItemNode skyPiercer = new ItemNode("Sky Piercer", "Final Item (Best for Finishers)");
        skyPiercer.addChild(expertGloves);
        skyPiercer.addChild(expertGloves);

        ItemNode fleetingTime = new ItemNode("Fleeting Time", "Final Item (Ultimate CD Reduction)");
        fleetingTime.addChild(herosRing);
        fleetingTime.addChild(herosRing);
        fleetingTime.addChild(expertGloves);

        ItemNode winterCrown = new ItemNode("Winter Crown", "Final Item (Temporary Invisibility)"); 
        winterCrown.addChild(expertGloves);
        winterCrown.addChild(herosRing);
        winterCrown.addChild(vitalityCrystal);

        ItemNode clockOfDestiny = new ItemNode("Clock of Destiny", "Final Item (For Magic Tanks)");
        clockOfDestiny.addChild(elegantGem);
        clockOfDestiny.addChild(bookOfSages);
        clockOfDestiny.addChild(bookOfSages);

        ItemNode divineGlaive = new ItemNode("Divine Glaive", "Final Item (Magic Penetration)");
        divineGlaive.addChild(magicWand);

        ItemNode holyCrystal = new ItemNode("Holy Crystal", "Final Item (Max Magic Power)");
        holyCrystal.addChild(magicWand);
        holyCrystal.addChild(magicWand);
        holyCrystal.addChild(magicWand); // Accurately requires 3 Magic Wands!

        ItemNode concentratedEnergy = new ItemNode("Concentrated Energy", "Final Item (Continuous Damage Boost)");
        concentratedEnergy.addChild(mysticContainer);
        concentratedEnergy.addChild(magicWand);
        concentratedEnergy.addChild(vitalityCrystal);

        ItemNode iceQueenWand = new ItemNode("Ice Queen Wand", "Final Item (Slow on Skill Hit)");
        iceQueenWand.addChild(mysticContainer);
        iceQueenWand.addChild(exoticVeil);
        iceQueenWand.addChild(magicWand);

        ItemNode glowingWand = new ItemNode("Glowing Wand", "Final Item (Healing Reduction)");
        glowingWand.addChild(exoticVeil);
        glowingWand.addChild(mysteryCodex);
        glowingWand.addChild(vitalityCrystal);

        ItemNode starliumScythe = new ItemNode("Starlium Scythe", "Final Item (Enhanced Basic Attacks)");
        starliumScythe.addChild(azureBlade);
        starliumScythe.addChild(mysticContainer);
        starliumScythe.addChild(magicWand);

        ItemNode bloodWings = new ItemNode("Blood Wings", "Final Item (Speed Boost Shield)");
        bloodWings.addChild(magicWand);
        bloodWings.addChild(magicWand);

        ItemNode lightningTruncheon = new ItemNode("Lightning Truncheon", "Final Item (Splash Damage)");
        lightningTruncheon.addChild(magicWand);
        lightningTruncheon.addChild(bookOfSages);
        lightningTruncheon.addChild(powerCrystal);

        ItemNode geniusWand = new ItemNode("Genius Wand", "Final Item (Magic Defense Reduction)");
        geniusWand.addChild(exoticVeil);
        geniusWand.addChild(magicWand);

        ItemNode flaskOfTheOasis = new ItemNode("Flask of the Oasis", "Final Item (Protect Teammates)");
        flaskOfTheOasis.addChild(magicWand);
        flaskOfTheOasis.addChild(bookOfSages);
        flaskOfTheOasis.addChild(vitalityCrystal);

        ItemNode wishingLantern = new ItemNode("Wishing Lantern", "Final Item (Counter High HP)");
        wishingLantern.addChild(magicWand);
        wishingLantern.addChild(bookOfSages);
        wishingLantern.addChild(powerCrystal);

        ItemNode immortality = new ItemNode("Immortality", "Final Item (Self Resurrection)");
        immortality.addChild(aresBelt);
        immortality.addChild(vitalityCrystal);
        immortality.addChild(leatherJerkin);

        // 4. THE ROOT NODE 
        ItemNode magicShop = new ItemNode("Magic Items Shop", "Store Database");
        
        magicShop.addChild(enchantedTalisman);
        magicShop.addChild(featherOfHeaven);
        magicShop.addChild(skyPiercer);
        magicShop.addChild(fleetingTime);
        magicShop.addChild(winterCrown);
        magicShop.addChild(clockOfDestiny);
        magicShop.addChild(divineGlaive);
        magicShop.addChild(holyCrystal);
        magicShop.addChild(concentratedEnergy);
        magicShop.addChild(iceQueenWand);
        magicShop.addChild(glowingWand);
        magicShop.addChild(starliumScythe);
        magicShop.addChild(bloodWings);
        magicShop.addChild(lightningTruncheon);
        magicShop.addChild(geniusWand);
        magicShop.addChild(flaskOfTheOasis);
        magicShop.addChild(wishingLantern);
        magicShop.addChild(lanternOfHope); 
        magicShop.addChild(flowerOfHope);

        return magicShop;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        ItemNode rootDatabase = buildGlobalMagicTree();

        System.out.println("**************************************************");
        System.out.println("*        MAGIC EQUIPMENT CRAFTING SYSTEM         *");
        System.out.println("**************************************************\n");
        
        System.out.println("Available Final Items in Database:");
        int counter = 1;

        for (ItemNode finalItem : rootDatabase.children) {
            System.out.println("  [" + counter + "] " + finalItem.name);
            counter++;
        }

        System.out.println("\nNote: You can also search for any Tier 2 or Tier 1 component by name!");
        System.out.print("Enter an equipment name to inspect (e.g., book of sages, magic wand): ");
        String searchInput = scanner.nextLine().trim();

        ItemNode inspectedItem = findNode(rootDatabase, searchInput);

        if (inspectedItem == null) {
            System.out.println("\n[ERROR] Equipment '" + searchInput + "' could not be found in the database.");
            scanner.close();
            return;
        }

        System.out.println("\n--------------------------------------------------");
        System.out.println("INSPECTION REPORT FOR: " + inspectedItem.name.toUpperCase());
        System.out.println("--------------------------------------------------");

        System.out.println("\n<<< CRAFTING BLUEPRINT >>>");
        printTree(inspectedItem, 0);

        System.out.println("\n<<< COMPONENT PATHS >>>");
        printAllBuildPaths(inspectedItem, new ArrayList<>());

        System.out.println("\n<<< RECIPE METRICS >>>");
        System.out.println(">> Total Required Components : " + countNodes(inspectedItem));
        System.out.println(">> Base Materials (Tier 1)   : " + countLeaves(inspectedItem));
        System.out.println(">> Crafting Depth (Height)   : " + height(inspectedItem));
        
        System.out.println("\n<<< SPECIFIC COMPONENT SEARCH >>>");
        System.out.print("Enter a specific component to find inside " + inspectedItem.name + " (or press Enter to skip): ");
        String targetComponent = scanner.nextLine().trim();

        if (!targetComponent.isEmpty()) {
            List<String> specificPath = new ArrayList<>();
            boolean isFound = findPath(inspectedItem, targetComponent, specificPath);
            
            if (isFound) {
                System.out.println(">> Component Found! Crafting route:");
                System.out.println(">> " + String.join(" -> ", specificPath));
            } else {
                System.out.println(">> Component '" + targetComponent + "' is NOT required to build " + inspectedItem.name + ".");
            }
        }

        System.out.print("Enter a component to count its occurrences inside " + inspectedItem.name + ": ");
        String countTarget = scanner.nextLine().trim();
        
        if (!countTarget.isEmpty()) {
            int occurrences = countItemOccurrences(inspectedItem, countTarget);
            System.out.println(">> The component '" + countTarget + "' appears " + occurrences + " time(s) in this recipe.");
        }

       
        System.out.println("Searching Immortality...");
        printPathsEndingWith(rootDatabase, new ArrayList<>(), "Immortality");

        scanner.close();
    }
}