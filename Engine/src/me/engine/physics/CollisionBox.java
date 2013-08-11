package me.engine.physics;

public class CollisionBox implements CollisionShape
{
	private int x, y, x2, y2;
	
	public CollisionBox()
	{
		
	}
	
	public CollisionBox(int x, int y, int x2, int y2)
	{
		this.x = x;
		this.y = y;
		this.x2 = x2;
		this.y2 = y2;
	}
	
	public boolean isHoldingUp(CollisionBox box2)
	{
		if(box2.getY2() + 1 > getY())
		{
			return box2.getX2() > getX() & box2.getX() < getX2() &&
						true & box2.getY2() <= getY();
		}
		else
		{
			return false;
		}
	}
	
	public boolean isHoldingDown(CollisionBox box2)
	{
		if(box2.getY() - 1 < getY2())
		{
			return box2.getX2() > getX() & box2.getX() < getX2() &&
						true & box2.getY() >= getY2();
		}
		else
		{
			return false;
		}
	}
	
	public boolean isHoldingLeft(CollisionBox box2)
	{
		if(box2.getX2() + 1 > getX())
		{
			return box2.getY2() > getY() & box2.getY() < getY2() &&
						true & box2.getX2() <= getX();
		}
		else
		{
			return false;
		}
	}
	
	public boolean isHoldingRight(CollisionBox box2)
	{
		if(box2.getX() - 1 < getX2())
		{
			return box2.getY2() > getY() & box2.getY() < getY2() &&
						true & box2.getX() >= getX2();
		}
		else
		{
			return false;
		}
	}
	
	public boolean intersect(CollisionBox box)
	{
		return getX() <= box.getX2() & getX2() >= box.getX() &
				getY() <= box.getY2() & getY2() >= box.getY();
	}
	
	public void setX(int x)
	{
		this.x = x;
	}

	public int getX()
	{
		return x;
	}

	public void setY(int y)
	{
		this.y = y;
	}

	public int getY()
	{
		return y;
	}

	public void setX2(int x2)
	{
		this.x2 = x2;
	}

	public int getX2()
	{
		return x2;
	}

	public void setY2(int y2)
	{
		this.y2 = y2;
	}

	public int getY2()
	{
		return y2;
	}
	
	public void setWidth(int width)
	{
		setX2(getX() + width);
	}
	
	public int getWidth()
	{
		return getX2() - getX();
	}
	
	public void setHeight(int height)
	{
		setY2(getY() + height);
	}
	
	public int getHeight()
	{
		return getY2() - getY();
	}

	
	@Override
	public boolean isHoldingUp(CollisionShape shape)
	{
		if(shape instanceof CollisionBox)
		{
			return isHoldingUp((CollisionBox) shape);
		}
		else
		{
			return false;
		}
	}

	
	@Override
	public boolean isHoldingDown(CollisionShape shape)
	{
		if(shape instanceof CollisionBox)
		{
			return isHoldingDown((CollisionBox) shape);
		}
		else
		{
			return false;
		}
	}
	

	@Override
	public boolean isHoldingLeft(CollisionShape shape)
	{
		if(shape instanceof CollisionBox)
		{
			return isHoldingLeft((CollisionBox) shape);
		}
		else
		{
			return false;
		}
	}
	

	@Override
	public boolean isHoldingRight(CollisionShape shape)
	{
		if(shape instanceof CollisionBox)
		{
			return isHoldingRight((CollisionBox) shape);
		}
		else
		{
			return false;
		}
	}
	

	@Override
	public boolean intersect(CollisionShape shape)
	{
		if(shape instanceof CollisionBox)
		{
			return intersect((CollisionBox) shape);
		}
		else
		{
			return false;
		}
	}
}
