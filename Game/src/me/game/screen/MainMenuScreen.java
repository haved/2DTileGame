package me.game.screen;

import java.io.File;

import me.engine.asset.AssetPack;
import me.engine.asset.PackLoader;
import me.engine.effect.FlipTransistion;
import me.engine.guiobject.ButtonListener;
import me.engine.guiobject.GuiObjectButton;
import me.engine.math.RectangleI;
import me.engine.render.RenderEngine;
import me.engine.screen.ContainerGuiScreen;
import me.engine.screen.LoadingScreen;
import me.engine.screen.WorldLoaderScreen;
import me.game.core.MainGame;
import me.game.world.WorldDebugger;
import me.game.world.entity.MyPlayerEntity;

public class MainMenuScreen extends ContainerGuiScreen implements ButtonListener
{
	private BarLoadingScreen loading;
	private PackLoader packs;
	private GuiObjectButton[] buttons;
	private FlipTransistion flip;
	
	public MainMenuScreen()
	{
		packs = new PackLoader();
		
		loading = new BarLoadingScreen(packs);
		
		packs.addAssetPack(new AssetPack("MainMenu", new File(MainGame.assetLoaction + "MainMenu")));
		packs.addAssetPack(new AssetPack("Gui", new File(MainGame.assetLoaction + "gui")));
		initButtons();
	}
	
	private void initButtons()
	{
		buttons = new GuiObjectButton[2];
		
		buttons[0] = new GuiObjectButton("New Game", "Gui;gui.png");
		buttons[0].setListener(this);
		buttons[0].setBounds(new RectangleI(300, 300, 424, 40));
		add(buttons[0]);
		
		buttons[1] = new GuiObjectButton("Load Game", "Gui;gui.png");
		buttons[1].setListener(this);
		buttons[1].setBounds(new RectangleI(300, 380, 424, 40));
		add(buttons[1]);
	}
	
	@Override
	public void offFocus()
	{
		super.offFocus();
		
		packs.unload();
	}
	
	@Override
	public void update()
	{
		if(packs.tryWork()) return;
		
		if(flip != null)
		{
			if(flip.isDone())
			{
				startNewGame();
			}
			
			flip.tick();
		}
		
		updateObjects();
	}
	
	@Override
	public void render()
	{
		RenderEngine.resetColor();
		
		RenderEngine.bind("MainMenu;bg.png");
		RenderEngine.drawTexture(new RectangleI(0, 0, 1024, 640), new RectangleI(0, 0, 800, 640));
		
		renderObjects();
		
		if(flip != null)
		{
			flip.render();
		}
	}

	@Override
	public boolean hasLoadingScreen()
	{
		return packs.hasWork();
	}
	
	@Override
	public LoadingScreen getLoadingScreen()
	{
		return loading;
	}

	public void onButtonPress(GuiObjectButton source)
	{
		if(source == buttons[0])
		{
			flip = new FlipTransistion(500, false);
		}
	}
	
	private void startNewGame()
	{
		PackLoader pack = new PackLoader();
		pack.addAssetPack(new AssetPack("Tutorial", new File(MainGame.assetLoaction + "worlds/Tutorial")));
		
		WorldLoaderScreen screen = new WorldLoaderScreen(pack, "Tutorial;StartingRoom.map",
														"Start", MyPlayerEntity.class);
		screen.setWorldInit(new WorldDebugger());
		
		canvas.setGuiScreen(screen);
	}
}
