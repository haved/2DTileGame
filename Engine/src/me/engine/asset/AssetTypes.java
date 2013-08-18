package me.engine.asset;

public enum AssetTypes
{
	TEXTURE(TextureAsset.class, "PNG");
	
	private Class<? extends Asset> clazz;
	private String fileType;
	
	private AssetTypes(Class<? extends Asset> clazz, String fileType)
	{
		this.clazz = clazz;
		this.setFileType(fileType);
	}
	
	public Asset makeInstance()
	{
		try
		{
			return clazz.newInstance();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return null;
	}

	public String getFileType()
	{
		return fileType;
	}

	public void setFileType(String fileType)
	{
		this.fileType = fileType;
	}
}
