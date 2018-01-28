package zabi.minecraft.extraalchemy.recipes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.potion.PotionType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import zabi.minecraft.extraalchemy.ExtraAlchemy;
import zabi.minecraft.extraalchemy.items.ModItems;
import zabi.minecraft.extraalchemy.lib.Config;
import zabi.minecraft.extraalchemy.lib.Log;
import zabi.minecraft.extraalchemy.lib.Reference;
import zabi.minecraft.extraalchemy.potion.PotionReference;
import zabi.minecraft.extraalchemy.recipes.brew.RecipeManager;
import zabi.minecraft.extraalchemy.recipes.crafting.QuickVialRecipeHandler;
import zabi.minecraft.extraalchemy.recipes.crafting.SplitPotionRecipeHandler;
import zabi.minecraft.extraalchemy.recipes.crafting.StickyPotionRecipeHandler;

@Mod.EventBusSubscriber
public class Recipes {
	
	private static int customRecipes = 0;
	
	public static void registerRecipes() {
		
		loadCustomRecipes();
		
		if (!(Config.loadAsCovensPlugin.getBoolean() && Loader.isModLoaded("covens"))) {
		
			//3 Variants
			if (Config.p_fuse.getBoolean()) 				RecipeManager.registerRecipeWithVariant(PotionTypes.AWKWARD, new ItemStack(Items.FIREWORK_CHARGE), PotionReference.INSTANCE.TYPE_FUSE_NORMAL, PotionReference.INSTANCE.TYPE_FUSE_QUICK, PotionReference.INSTANCE.TYPE_FUSE_STRONG);
			if (Config.p_magnetism.getBoolean()) 		RecipeManager.registerRecipeWithVariant(PotionTypes.AWKWARD, new ItemStack(Items.IRON_INGOT), PotionReference.INSTANCE.TYPE_MAGNETISM_NORMAL, PotionReference.INSTANCE.TYPE_MAGNETISM_LONG, PotionReference.INSTANCE.TYPE_MAGNETISM_STRONG);
			if (Config.p_piper.getBoolean()) 			RecipeManager.registerRecipeWithVariant(PotionTypes.AWKWARD, new ItemStack(Items.WHEAT), PotionReference.INSTANCE.TYPE_PIPER_NORMAL, PotionReference.INSTANCE.TYPE_PIPER_LONG, PotionReference.INSTANCE.TYPE_PIPER_STRONG);
			if (Config.p_photosynthesis.getBoolean()) 	RecipeManager.registerRecipeWithVariant(PotionTypes.AWKWARD, new ItemStack(Items.BEETROOT_SEEDS), PotionReference.INSTANCE.TYPE_PHOTOSYNTHESIS_NORMAL, PotionReference.INSTANCE.TYPE_PHOTOSYNTHESIS_LONG, PotionReference.INSTANCE.TYPE_PHOTOSYNTHESIS_STRONG);
			if (Config.p_reincarnation.getBoolean())		RecipeManager.registerRecipeWithVariant(PotionTypes.AWKWARD, new ItemStack(Items.LEATHER), PotionReference.INSTANCE.TYPE_REINCARNATION_NORMAL, PotionReference.INSTANCE.TYPE_REINCARNATION_LONG, PotionReference.INSTANCE.TYPE_REINCARNATION_STRONG);

			if (Config.p_dislocation.getBoolean())	 	RecipeManager.registerRecipeWithVariant(PotionTypes.MUNDANE, new ItemStack(Items.CHORUS_FRUIT), PotionReference.INSTANCE.TYPE_DISLOCATION_NORMAL, PotionReference.INSTANCE.TYPE_DISLOCATION_LONG, PotionReference.INSTANCE.TYPE_DISLOCATION_STRONG);
			if (Config.p_combustion.getBoolean()) 		RecipeManager.registerRecipeWithVariant(PotionTypes.MUNDANE, new ItemStack(Item.getItemFromBlock(Blocks.COAL_BLOCK)), PotionReference.INSTANCE.TYPE_COMBUSTION_NORMAL, PotionReference.INSTANCE.TYPE_COMBUSTION_LONG, PotionReference.INSTANCE.TYPE_COMBUSTION_STRONG);

			if (Config.p_learning.getBoolean())			RecipeManager.registerRecipeWithVariant(PotionTypes.THICK, new ItemStack(Item.getItemFromBlock(Blocks.LAPIS_BLOCK)), PotionReference.INSTANCE.TYPE_LEARNING_NORMAL, PotionReference.INSTANCE.TYPE_LEARNING_LONG, PotionReference.INSTANCE.TYPE_LEARNING_STRONG);
			if (Config.p_gravity.getBoolean()) 			RecipeManager.registerRecipeWithVariant(PotionTypes.THICK, new ItemStack(Items.NETHERBRICK), PotionReference.INSTANCE.TYPE_GRAVITY_NORMAL, PotionReference.INSTANCE.TYPE_GRAVITY_LONG, PotionReference.INSTANCE.TYPE_GRAVITY_STRONG);
			if (Config.p_crumbling.getBoolean()) 		RecipeManager.registerRecipeWithVariant(PotionTypes.THICK, new ItemStack(Items.FLINT), PotionReference.INSTANCE.TYPE_CRUMBLING_NORMAL, PotionReference.INSTANCE.TYPE_CRUMBLING_LONG, PotionReference.INSTANCE.TYPE_CRUMBLING_STRONG);

			if (Config.p_recall.getBoolean()) 			RecipeManager.registerRecipeWithVariant(PotionTypes.LONG_SLOWNESS, new ItemStack(Items.ENDER_EYE), PotionReference.INSTANCE.TYPE_RECALL_NORMAL, PotionReference.INSTANCE.TYPE_RECALL_LONG, PotionReference.INSTANCE.TYPE_RECALL_STRONG);
			if (Config.p_sinking.getBoolean()) 			RecipeManager.registerRecipeWithVariant(PotionTypes.WATER_BREATHING, new ItemStack(Items.CLAY_BALL), PotionReference.INSTANCE.TYPE_SINKING_NORMAL, PotionReference.INSTANCE.TYPE_SINKING_LONG, PotionReference.INSTANCE.TYPE_SINKING_STRONG);
			if (Config.p_pacifism.getBoolean()) 			RecipeManager.registerRecipeWithVariant(PotionTypes.STRONG_HARMING, new ItemStack(Items.GOLDEN_APPLE), PotionReference.INSTANCE.TYPE_PACIFISM_NORMAL, PotionReference.INSTANCE.TYPE_PACIFISM_LONG, PotionReference.INSTANCE.TYPE_PACIFISM_STRONG);
			if (Config.p_hurry.getBoolean()) 			RecipeManager.registerRecipeWithVariant(PotionReference.INSTANCE.TYPE_CHARGED2, new ItemStack(Items.COOKIE), PotionReference.INSTANCE.TYPE_HURRY_NORMAL, PotionReference.INSTANCE.TYPE_HURRY_LONG, PotionReference.INSTANCE.TYPE_HURRY_STRONG);
			if (Config.p_leech.getBoolean())				RecipeManager.registerRecipeWithVariant(PotionTypes.HARMING, new ItemStack(Items.SPECKLED_MELON), PotionReference.INSTANCE.TYPE_LEECH_NORMAL, PotionReference.INSTANCE.TYPE_LEECH_LONG, PotionReference.INSTANCE.TYPE_LEECH_STRONG);
			if (Config.p_sails.getBoolean())				RecipeManager.registerRecipeWithVariant(PotionTypes.LONG_SWIFTNESS, new ItemStack(Items.FISH), PotionReference.INSTANCE.TYPE_SAILS_NORMAL, PotionReference.INSTANCE.TYPE_SAILS_LONG, PotionReference.INSTANCE.TYPE_SAILS_STRONG);
			if (Config.p_beheading.getBoolean())			RecipeManager.registerRecipeWithVariant(PotionReference.INSTANCE.TYPE_CHARGED2, new ItemStack(Items.ROTTEN_FLESH), PotionReference.INSTANCE.TYPE_BEHEADING_NORMAL, PotionReference.INSTANCE.TYPE_BEHEADING_LONG, PotionReference.INSTANCE.TYPE_BEHEADING_STRONG);

			//1 Variant
			if (Config.p_concentration.getBoolean()) 	RecipeManager.registerRecipe(PotionTypes.AWKWARD, PotionReference.INSTANCE.TYPE_CONCENTRATION, Items.EGG);
			if (Config.p_freezing.getBoolean())			RecipeManager.registerRecipe(PotionTypes.AWKWARD, PotionReference.INSTANCE.TYPE_FREEZING, Items.SNOWBALL);
			if (Config.p_return.getBoolean())			RecipeManager.registerRecipe(PotionTypes.AWKWARD, PotionReference.INSTANCE.TYPE_RETURN, Items.PRISMARINE_SHARD);
			if (Config.p_cheatDeath.getBoolean())		RecipeManager.registerRecipe(PotionReference.INSTANCE.TYPE_CHARGED2, PotionReference.INSTANCE.TYPE_CHEAT_DEATH, Items.GOLDEN_APPLE);
			if (Config.p_detection.getBoolean()) 		RecipeManager.registerRecipe(PotionTypes.MUNDANE, PotionReference.INSTANCE.TYPE_DETECTION, Items.SPIDER_EYE);
			if (Config.p_dispel.getBoolean())			RecipeManager.registerRecipe(PotionReference.INSTANCE.TYPE_LEARNING_STRONG, PotionReference.INSTANCE.TYPE_DISPEL, Items.POISONOUS_POTATO);
			if (Config.p_charged_level2.getBoolean()) 	RecipeManager.registerRecipe(PotionReference.INSTANCE.TYPE_CHARGED, PotionReference.INSTANCE.TYPE_CHARGED2, Items.PRISMARINE_CRYSTALS);
			if (Config.p_charged_level1.getBoolean()) 	RecipeManager.registerRecipe(PotionTypes.THICK, PotionReference.INSTANCE.TYPE_CHARGED, Items.GOLD_INGOT);

		}

		if (Config.breakingPotions.getBoolean() && !(Config.loadAsCovensPlugin.getBoolean() && Loader.isModLoaded("covens"))) {
			GameRegistry.addShapedRecipe(new ResourceLocation(Reference.MID, "vial"), null, new ItemStack(ModItems.vial_break, 3)," F ","G G"," G ", 'G', Item.getItemFromBlock(Blocks.GLASS), 'F', Items.FLINT);
		}
		if (Config.enable_potion_bag.getBoolean() && !(Config.loadAsCovensPlugin.getBoolean() && Loader.isModLoaded("covens"))) {
			GameRegistry.addShapedRecipe(new ResourceLocation(Reference.MID, "recipe_potion_bag"), null, new ItemStack(ModItems.potion_bag), " V ","LWL","LLL", 'V', Items.GLASS_BOTTLE, 'W', Item.getItemFromBlock(Blocks.CHEST), 'L', Items.LEATHER);
		}
		
	}

	private static void loadCustomRecipes() {
		File file = new File(ExtraAlchemy.recipesfile);
		if (!file.exists()) {
			try {
				if (file.createNewFile()) {
					initFile(file);
				} else {
					return;
				}
			} catch (IOException e) {
				Log.w("Cannot create custom recipe file");
				e.printStackTrace();
				return;
			}
		}

		readFile(file);
	}

	private static void readFile(File file) {
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(file));
			in.lines().filter(s -> !s.startsWith("#")).forEach(s -> parseString(s));
			Log.i(customRecipes+" custom recipes were added");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			Log.w("Cannot read custom recipes file");
		}
		
		if (in!=null) try {
			in.close();
		} catch (IOException e) {}

	}

	private static void parseString(String s) {
		if (s.trim().length()==0) return;
		try {
			int amp = s.indexOf('&');
			int eql = s.indexOf('=');
			int cmm = s.indexOf(',');

			String spotin = s.substring(0, amp).trim();
			String spotout = s.substring(eql+1, s.length()).trim();
			
			String ingredient_raw = s.substring(amp+1, eql).trim();

			String item = "";
			int meta = 0;

			if (cmm<0) {
				item = ingredient_raw;
			} else {
				item=s.substring(amp+1,cmm).trim();
				meta = Integer.parseInt(s.substring(cmm+1,eql).trim());
			}
			
			PotionType pin = PotionType.REGISTRY.getObject(new ResourceLocation(spotin));
			PotionType pout= PotionType.REGISTRY.getObject(new ResourceLocation(spotout));

			if (pin.equals(PotionTypes.WATER) || pout.equals(PotionTypes.WATER)) {
				Log.w("Input ("+spotin+") or output ("+spotout+") potion equals to water in "+s);
			}

			Item ingItm = Item.REGISTRY.getObject(new ResourceLocation(item));
			if (ingItm == null || ingItm.equals(Items.AIR)) {
				ingItm = Item.getItemFromBlock(Block.REGISTRY.getObject(new ResourceLocation(item)));
				if (ingItm == null || ingItm.equals(Items.AIR)) {
					Log.e(item+" is not an existing item in "+s);
					return;
				}
			}

			ItemStack req = new ItemStack(ingItm, 1, meta);
			if (req.isEmpty()) {
				Log.e(item+" is not a valid item in "+s);
				return;
			}

			RecipeManager.registerRecipe(pin, pout, req);
			Log.i("Custom recipe found: "+pin.getRegistryName().toString()+" + "+req.toString()+" = "+pout.getRegistryName());
			customRecipes++;

		} catch (Exception e) {
			Log.w(s+" is not a valid custom recipe");
			e.printStackTrace();
		}
	}

	private static void initFile(File file) {
		try {
			PrintWriter out = new PrintWriter(file);
			out.println("# Recipe format: potionIn&Itemstack,meta=potionOut");
			out.println("# If the item has no meta you can omit it: minecraft:awkward&minecraft:dirt=minecraft:water");
			out.println("# Example: minecraft:awkward&minecraft:dye,3=minecraft:strong_healing would take an awkward potion,");
			out.println("# cocoa (dye with metadata 3), and make a potion of healing II out of it.");
			out.println("# To log all existing potions set the \"log_potion_types\" config to true.");
			out.println("# To remove an existing potion recipe disable it in the config file.");
			out.println("# Lines starting with # and empty lines are ignored.");
			out.println("# There is no difference between \"minecraft:awkward&minecraft:dye,3=minecraft:strong_healing\" and \"minecraft:awkward & minecraft:dye,3 = minecraft:strong_healing\", spaces near delimiters are ignored");
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}
	}
	
	@SubscribeEvent
	public static void registerRecipes(RegistryEvent.Register<IRecipe> evt) {
		IForgeRegistry<IRecipe> reg = evt.getRegistry();
		if (!(Config.loadAsCovensPlugin.getBoolean() && Loader.isModLoaded("covens"))) {
			if (Config.allowPotionCombining.getBoolean()) reg.register(new StickyPotionRecipeHandler());
			if (Config.allowPotionSplitting.getBoolean()) reg.register(new SplitPotionRecipeHandler());
			if (Config.breakingPotions.getBoolean()) reg.register(new QuickVialRecipeHandler());
		}
	}
}