package samples.objLoader;

import org.lwjgl.input.Keyboard;

import engine.buffers.PatchVAO;
import engine.configs.AlphaBlending;
import engine.core.Input;
import engine.math.Vec2f;
import engine.scenegraph.GameObject;
import engine.scenegraph.components.Material;
import engine.scenegraph.components.RenderInfo;
import engine.scenegraph.components.Renderer;
import engine.shader.basic.TessellationGrid;
import engine.shader.blinnphong.Tessellation;
import engine.texturing.Texture;

public class Logo extends GameObject{

	public Logo(){
		
		getTransform().setLocalScaling(1000, 1000, 1000);
		
		PatchVAO meshBuffer = new PatchVAO();
		meshBuffer.addData(generatePatch2D4x4(),16);
		setRenderInfo(new RenderInfo(new AlphaBlending(0.0f), Tessellation.getInstance()));
		Renderer renderer = new Renderer(Tessellation.getInstance(), meshBuffer);
		
		Material material = new Material();
		material.setDiffusemap(new Texture("./res/textures/logo/eye.png"));
		material.setNormalmap(new Texture("./res/textures/logo/eye_NRM.jpg"));
		material.setDisplacemap(new Texture("./res/textures/logo/eye_DISP.jpg"));
		material.setSpecularmap(new Texture("./res/textures/logo/eye_SPEC.jpg"));
		material.setDisplaceScale(120);
		material.setEmission(0f);
		material.setShininess(100);
		material.getDiffusemap().bind();
		material.getDiffusemap().mipmap();
		material.getNormalmap().bind();
		material.getNormalmap().mipmap();
		material.getDisplacemap().bind();
		material.getDisplacemap().mipmap();
		material.getSpecularmap().bind();
		material.getSpecularmap().mipmap();
		
		addComponent("Material", material);
		addComponent("Renderer", renderer);	
	}
	
	public static Vec2f[] generatePatch2D4x4()
	{
		
		int amountx = 10; 
		int amounty = 10;
		
		// 16 vertices for each patch
		Vec2f[] vertices = new Vec2f[amountx * amounty * 16];
		
		int index = 0;
		float dx = 1f/amountx;
		float dy = 1f/amounty;
		
		for (float i=0; i<1; i+=dx)
		{
			for (float j=0; j<1; j+=dy)
			{	
				vertices[index++] = new Vec2f(i,j);
				vertices[index++] = new Vec2f(i+dx*0.33f,j);
				vertices[index++] = new Vec2f(i+dx*0.66f,j);
				vertices[index++] = new Vec2f(i+dx,j);
				
				vertices[index++] = new Vec2f(i,j+dy*0.33f);
				vertices[index++] = new Vec2f(i+dx*0.33f,j+dy*0.33f);
				vertices[index++] = new Vec2f(i+dx*0.66f,j+dy*0.33f);
				vertices[index++] = new Vec2f(i+dx,j+dy*0.33f);
				
				vertices[index++] = new Vec2f(i,j+dy*0.66f);
				vertices[index++] = new Vec2f(i+dx*0.33f,j+dy*0.66f);
				vertices[index++] = new Vec2f(i+dx*0.66f,j+dy*0.66f);
				vertices[index++] = new Vec2f(i+dx,j+dy*0.66f);
				
				vertices[index++] = new Vec2f(i,j+dy);
				vertices[index++] = new Vec2f(i+dx*0.33f,j+dy);
				vertices[index++] = new Vec2f(i+dx*0.66f,j+dy);
				vertices[index++] = new Vec2f(i+dx,j+dy);
			}
		}
		
		return vertices;
	}
	
	public void update(){
		super.update();
		
		if (Input.getHoldingKey(Keyboard.KEY_G))
		{
			((Renderer) getComponent("Renderer")).setShader(TessellationGrid.getInstance());
		}
		else
			((Renderer) getComponent("Renderer")).setShader(Tessellation.getInstance());
	}
}
