/*
 * $Id$
 * The Chmod Calculator
 * Copyright (C) 2010 Lloyd S. Dilley <lloyd@dilley.me>
 * http://www.dilley.me/
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

/**
 * @author Lloyd S. Dilley
 */

package me.dilley.chmodcalculator;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.text.method.LinkMovementMethod;
import android.text.SpannableString;
import android.text.util.Linkify;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public final class ChmodCalculator extends Activity
{
  private static byte specialTotal=0;
  private static byte userTotal=0;
  private static byte groupTotal=0;
  private static byte otherTotal=0;

  private static Button clear=null;

  private static ImageButton upSpecialBits=null;
  private static ImageButton downSpecialBits=null;
  private static ImageButton upUserBits=null;
  private static ImageButton downUserBits=null;
  private static ImageButton upGroupBits=null;
  private static ImageButton downGroupBits=null;
  private static ImageButton upOtherBits=null;
  private static ImageButton downOtherBits=null;

  private static CheckBox setuid=null;
  private static CheckBox userRead=null;
  private static CheckBox userWrite=null;
  private static CheckBox userExec=null;
  private static CheckBox setgid=null;
  private static CheckBox groupRead=null;
  private static CheckBox groupWrite=null;
  private static CheckBox groupExec=null;
  private static CheckBox otherRead=null;
  private static CheckBox otherWrite=null;
  private static CheckBox otherExec=null;
  private static CheckBox sticky=null;
  
  private static EditText specialBits=null;
  private static EditText userBits=null;
  private static EditText groupBits=null;
  private static EditText otherBits=null;


  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    clear=(Button)this.findViewById(R.id.clear);

    upSpecialBits=(ImageButton)this.findViewById(R.id.upSpecialBits);
    downSpecialBits=(ImageButton)this.findViewById(R.id.specialDown);
    upUserBits=(ImageButton)this.findViewById(R.id.userUp);
    downUserBits=(ImageButton)this.findViewById(R.id.userDown);
    upGroupBits=(ImageButton)this.findViewById(R.id.groupUp);
    downGroupBits=(ImageButton)this.findViewById(R.id.groupDown);
    upOtherBits=(ImageButton)this.findViewById(R.id.otherUp);
    downOtherBits=(ImageButton)this.findViewById(R.id.otherDown);

    setuid=(CheckBox)this.findViewById(R.id.sucb);
    userRead=(CheckBox)this.findViewById(R.id.urcb);
    userWrite=(CheckBox)this.findViewById(R.id.uwcb);
    userExec=(CheckBox)this.findViewById(R.id.uecb);
    setgid=(CheckBox)this.findViewById(R.id.sgcb);
    groupRead=(CheckBox)this.findViewById(R.id.grcb);
    groupWrite=(CheckBox)this.findViewById(R.id.gwcb);
    groupExec=(CheckBox)this.findViewById(R.id.gecb);
    otherRead=(CheckBox)this.findViewById(R.id.orcb);
    otherWrite=(CheckBox)this.findViewById(R.id.owcb);
    otherExec=(CheckBox)this.findViewById(R.id.oecb);
    sticky=(CheckBox)this.findViewById(R.id.sbcb);

    specialBits=(EditText)this.findViewById(R.id.specialBits);
    userBits=(EditText)this.findViewById(R.id.userBits);
    groupBits=(EditText)this.findViewById(R.id.groupBits);
    otherBits=(EditText)this.findViewById(R.id.otherBits);

    upSpecialBits.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View v)
      {
    	if(specialTotal==7)
        {
       	  specialTotal=0;
       	  updateCheckBoxes();
       	}
       	else
       	{
          specialTotal++;
          updateCheckBoxes();
        }
        specialBits.setText(Byte.toString(specialTotal));
      }
    });

    downSpecialBits.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View v)
      {
    	if(specialTotal==0)
        {
          specialTotal=7;
          updateCheckBoxes();
        }
        else
        {
          specialTotal--;
          updateCheckBoxes();
        }
        specialBits.setText(Byte.toString(specialTotal));
      }
    });

    upUserBits.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View v)
      {
     	if(userTotal==7)
       	{
      	  userTotal=0;
       	  updateCheckBoxes();
       	}
       	else
       	{
          userTotal++;
       	  updateCheckBoxes();
       	}
      	userBits.setText(Byte.toString(userTotal));
      }
    });

    downUserBits.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View v)
      {
     	if(userTotal==0)
       	{
       	  userTotal=7;
       	  updateCheckBoxes();
       	}
       	else
       	{
          userTotal--;
       	  updateCheckBoxes();
      	}
       	userBits.setText(Byte.toString(userTotal));
      }
    });

    upGroupBits.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View v)
      {
    	if(groupTotal==7)
      	{
      	  groupTotal=0;
       	  updateCheckBoxes();
       	}
       	else
       	{
          groupTotal++;
      	  updateCheckBoxes();
       	}
       	groupBits.setText(Byte.toString(groupTotal));
      }
    });

    downGroupBits.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View v)
      {
    	if(groupTotal==0)
      	{
       	  groupTotal=7;
       	  updateCheckBoxes();
       	}
       	else
       	{
          groupTotal--;
      	  updateCheckBoxes();
      	}
      	groupBits.setText(Byte.toString(groupTotal));
      }
    });

    upOtherBits.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View v)
      {
     	if(otherTotal==7)
      	{
       	  otherTotal=0;
       	  updateCheckBoxes();
      	}
       	else
       	{
          otherTotal++;
       	  updateCheckBoxes();
       	}
      	otherBits.setText(Byte.toString(otherTotal));
      }
    });

    downOtherBits.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View v)
      {
     	if(otherTotal==0)
      	{
       	  otherTotal=7;
       	  updateCheckBoxes();
       	}
      	else
       	{
          otherTotal--;
       	  updateCheckBoxes();
       	}
      	otherBits.setText(Byte.toString(otherTotal));
      }
    });

    setuid.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View v)
      {
        updateTotals();
      }
    });
        
    setgid.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View v)
      {
        updateTotals();
      }
    });
        
    sticky.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View v)
      {
        updateTotals();
      }
    });

    userRead.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View v)
      {
        updateTotals();
      }
    });

    userWrite.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View v)
      {
        updateTotals();
      }
    });

    userExec.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View v)
      {
        updateTotals();
      }
    });

    groupRead.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View v)
      {
        updateTotals();
      }
    });

    groupWrite.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View v)
      {
        updateTotals();
      }
    });

    groupExec.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View v)
      {
        updateTotals();
      }
    });

    otherRead.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View v)
      {
        updateTotals();
      }
    });

    otherWrite.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View v)
      {
        updateTotals();
      }
    });

    otherExec.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View v)
      {
        updateTotals();
      }
    });

    clear.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View v)
      {
        clearValues();
      }
    });
  }

  private static final void updateTotals()
  {
    if(sticky.isChecked() && setgid.isChecked() && setuid.isChecked())
    {
      specialTotal=7;
      specialBits.setText(Byte.toString(specialTotal));
    }
    else if(sticky.isChecked() && setgid.isChecked() && setuid.isChecked()==false)
    {
      specialTotal=3;
      specialBits.setText(Byte.toString(specialTotal));
    }
    else if(sticky.isChecked() && setgid.isChecked()==false && setuid.isChecked())
    {
      specialTotal=5;
      specialBits.setText(Byte.toString(specialTotal));
    }
    else if(sticky.isChecked() && setgid.isChecked()==false && setuid.isChecked()==false)
    {
      specialTotal=1;
      specialBits.setText(Byte.toString(specialTotal));
    }
    else if(sticky.isChecked()==false && setgid.isChecked() && setuid.isChecked()==false)
    {
      specialTotal=2;
      specialBits.setText(Byte.toString(specialTotal));
    }
    else if(sticky.isChecked()==false && setgid.isChecked() && setuid.isChecked())
    {
      specialTotal=6;
      specialBits.setText(Byte.toString(specialTotal));
    }
    else if(sticky.isChecked()==false && setgid.isChecked()==false && setuid.isChecked())
    {
      specialTotal=4;
      specialBits.setText(Byte.toString(specialTotal));
    }
    else if(sticky.isChecked()==false && setgid.isChecked()==false && setuid.isChecked()==false)
    {
      specialTotal=0;
      specialBits.setText(Byte.toString(specialTotal));
    }

    if(userExec.isChecked() && userWrite.isChecked() && userRead.isChecked())
    {
      userTotal=7;
      userBits.setText(Byte.toString(userTotal));
    }
    else if(userExec.isChecked() && userWrite.isChecked() && userRead.isChecked()==false)
    {
      userTotal=3;
      userBits.setText(Byte.toString(userTotal));
    }
    else if(userExec.isChecked() && userWrite.isChecked()==false && userRead.isChecked())
    {
      userTotal=5;
      userBits.setText(Byte.toString(userTotal));
    }
    else if(userExec.isChecked() && userWrite.isChecked()==false && userRead.isChecked()==false)
    {
      userTotal=1;
      userBits.setText(Byte.toString(userTotal));
    }
    else if(userExec.isChecked()==false && userWrite.isChecked() && userRead.isChecked()==false)
    {
      userTotal=2;
      userBits.setText(Byte.toString(userTotal));
    }
    else if(userExec.isChecked()==false && userWrite.isChecked() && userRead.isChecked())
    {
      userTotal=6;
      userBits.setText(Byte.toString(userTotal));
    }
    else if(userExec.isChecked()==false && userWrite.isChecked()==false && userRead.isChecked())
    {
      userTotal=4;
      userBits.setText(Byte.toString(userTotal));
    }
    else if(userExec.isChecked()==false && userWrite.isChecked()==false && userRead.isChecked()==false)
    {
      userTotal=0;
      userBits.setText(Byte.toString(userTotal));
    }

    if(groupExec.isChecked() && groupWrite.isChecked() && groupRead.isChecked())
    {
      groupTotal=7;
      groupBits.setText(Byte.toString(groupTotal));
    }
    else if(groupExec.isChecked() && groupWrite.isChecked() && groupRead.isChecked()==false)
    {
      groupTotal=3;
      groupBits.setText(Byte.toString(groupTotal));
    }
    else if(groupExec.isChecked() && groupWrite.isChecked()==false && groupRead.isChecked())
    {
      groupTotal=5;
      groupBits.setText(Byte.toString(groupTotal));
    }
    else if(groupExec.isChecked() && groupWrite.isChecked()==false && groupRead.isChecked()==false)
    {
      groupTotal=1;
      groupBits.setText(Byte.toString(groupTotal));
    }
    else if(groupExec.isChecked()==false && groupWrite.isChecked() && groupRead.isChecked()==false)
    {
      groupTotal=2;
      groupBits.setText(Byte.toString(groupTotal));
    }
    else if(groupExec.isChecked()==false && groupWrite.isChecked() && groupRead.isChecked())
    {
      groupTotal=6;
      groupBits.setText(Byte.toString(groupTotal));
    }
    else if(groupExec.isChecked()==false && groupWrite.isChecked()==false && groupRead.isChecked())
    {
      groupTotal=4;
      groupBits.setText(Byte.toString(groupTotal));
    }
    else if(groupExec.isChecked()==false && groupWrite.isChecked()==false && groupRead.isChecked()==false)
    {
      groupTotal=0;
      groupBits.setText(Byte.toString(groupTotal));
    }

    if(otherExec.isChecked() && otherWrite.isChecked() && otherRead.isChecked())
    {
      otherTotal=7;
      otherBits.setText(Byte.toString(otherTotal));
    }
    else if(otherExec.isChecked() && otherWrite.isChecked() && otherRead.isChecked()==false)
    {
      otherTotal=3;
      otherBits.setText(Byte.toString(otherTotal));
    }
    else if(otherExec.isChecked() && otherWrite.isChecked()==false && otherRead.isChecked())
    {
      otherTotal=5;
      otherBits.setText(Byte.toString(otherTotal));
    }
    else if(otherExec.isChecked() && otherWrite.isChecked()==false && otherRead.isChecked()==false)
    {
      otherTotal=1;
      otherBits.setText(Byte.toString(otherTotal));
    }
    else if(otherExec.isChecked()==false && otherWrite.isChecked() && otherRead.isChecked()==false)
    {
      otherTotal=2;
      otherBits.setText(Byte.toString(otherTotal));
    }
    else if(otherExec.isChecked()==false && otherWrite.isChecked() && otherRead.isChecked())
    {
      otherTotal=6;
      otherBits.setText(Byte.toString(otherTotal));
    }
    else if(otherExec.isChecked()==false && otherWrite.isChecked()==false && otherRead.isChecked())
    {
      otherTotal=4;
      otherBits.setText(Byte.toString(otherTotal));
    }
    else if(otherExec.isChecked()==false && otherWrite.isChecked()==false && otherRead.isChecked()==false)
    {
      otherTotal=0;
      otherBits.setText(Byte.toString(otherTotal));
    }
  }
    
  private static final void updateCheckBoxes()
  {
    if(specialTotal==0)
    {
      setuid.setChecked(false);
      setgid.setChecked(false);
      sticky.setChecked(false);
    }

    if(specialTotal==1)
    {
      setuid.setChecked(false);
      setgid.setChecked(false);
      sticky.setChecked(true);
    }

    if(specialTotal==2)
    {
      setuid.setChecked(false);
      setgid.setChecked(true);
      sticky.setChecked(false);
    }

    if(specialTotal==3)
    {
      setuid.setChecked(false);
      setgid.setChecked(true);
      sticky.setChecked(true);
    }

    if(specialTotal==4)
    {
      setuid.setChecked(true);
      setgid.setChecked(false);
      sticky.setChecked(false);
    }

    if(specialTotal==5)
    {
      setuid.setChecked(true);
      setgid.setChecked(false);
      sticky.setChecked(true);
    }

    if(specialTotal==6)
    {
      setuid.setChecked(true);
      setgid.setChecked(true);
      sticky.setChecked(false);
    }

    if(specialTotal==7)
    {
      setuid.setChecked(true);
      setgid.setChecked(true);
      sticky.setChecked(true);
    }

    if(userTotal==0)
    {
      userRead.setChecked(false);
      userWrite.setChecked(false);
      userExec.setChecked(false);
    }

    if(userTotal==1)
    {
      userRead.setChecked(false);
      userWrite.setChecked(false);
      userExec.setChecked(true);
    }

    if(userTotal==2)
    {
      userRead.setChecked(false);
      userWrite.setChecked(true);
      userExec.setChecked(false);
    }

    if(userTotal==3)
    {
      userRead.setChecked(false);
      userWrite.setChecked(true);
      userExec.setChecked(true);
    }

    if(userTotal==4)
    {
      userRead.setChecked(true);
      userWrite.setChecked(false);
      userExec.setChecked(false);
    }

    if(userTotal==5)
    {
      userRead.setChecked(true);
      userWrite.setChecked(false);
      userExec.setChecked(true);
    }

    if(userTotal==6)
    {
      userRead.setChecked(true);
      userWrite.setChecked(true);
      userExec.setChecked(false);
    }

    if(userTotal==7)
    {
      userRead.setChecked(true);
      userWrite.setChecked(true);
      userExec.setChecked(true);
    }

    if(groupTotal==0)
    {
      groupRead.setChecked(false);
      groupWrite.setChecked(false);
      groupExec.setChecked(false);
    }

    if(groupTotal==1)
    {
      groupRead.setChecked(false);
      groupWrite.setChecked(false);
      groupExec.setChecked(true);
    }

    if(groupTotal==2)
    {
      groupRead.setChecked(false);
      groupWrite.setChecked(true);
      groupExec.setChecked(false);
    }

    if(groupTotal==3)
    {
      groupRead.setChecked(false);
      groupWrite.setChecked(true);
      groupExec.setChecked(true);
    }

    if(groupTotal==4)
    {
      groupRead.setChecked(true);
      groupWrite.setChecked(false);
      groupExec.setChecked(false);
    }

    if(groupTotal==5)
    {
      groupRead.setChecked(true);
      groupWrite.setChecked(false);
      groupExec.setChecked(true);
    }

    if(groupTotal==6)
    {
      groupRead.setChecked(true);
      groupWrite.setChecked(true);
      groupExec.setChecked(false);
    }

    if(groupTotal==7)
    {
      groupRead.setChecked(true);
      groupWrite.setChecked(true);
      groupExec.setChecked(true);
    }

    if(otherTotal==0)
    {
      otherRead.setChecked(false);
      otherWrite.setChecked(false);
      otherExec.setChecked(false);
    }

    if(otherTotal==1)
    {
      otherRead.setChecked(false);
      otherWrite.setChecked(false);
      otherExec.setChecked(true);
    }

    if(otherTotal==2)
    {
      otherRead.setChecked(false);
      otherWrite.setChecked(true);
      otherExec.setChecked(false);
    }

    if(otherTotal==3)
    {
      otherRead.setChecked(false);
      otherWrite.setChecked(true);
      otherExec.setChecked(true);
    }

    if(otherTotal==4)
    {
      otherRead.setChecked(true);
      otherWrite.setChecked(false);
      otherExec.setChecked(false);
    }

    if(otherTotal==5)
    {
      otherRead.setChecked(true);
      otherWrite.setChecked(false);
      otherExec.setChecked(true);
    }

    if(otherTotal==6)
    {
      otherRead.setChecked(true);
      otherWrite.setChecked(true);
      otherExec.setChecked(false);
    }

    if(otherTotal==7)
    {
      otherRead.setChecked(true);
      otherWrite.setChecked(true);
      otherExec.setChecked(true);
    }
  }

  private static void clearValues()
  {
    setuid.setChecked(false);
    setgid.setChecked(false);
    sticky.setChecked(false);
    userRead.setChecked(false);
    userWrite.setChecked(false);
    userExec.setChecked(false);
    groupRead.setChecked(false);
    groupWrite.setChecked(false);
    groupExec.setChecked(false);
    otherRead.setChecked(false);
    otherWrite.setChecked(false);
    otherExec.setChecked(false);
    specialTotal=0;
    specialBits.setText(Byte.toString(specialTotal));
    userTotal=0;
    userBits.setText(Byte.toString(userTotal));
    groupTotal=0;
    groupBits.setText(Byte.toString(groupTotal));
    otherTotal=0;
    otherBits.setText(Byte.toString(otherTotal));
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu)
  {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.main_menu, menu);
    return true;
  }
    
  @Override
  public boolean onOptionsItemSelected(MenuItem item)
  {
    switch(item.getItemId())
    {
      case R.id.about:
        showAbout();
        return true;
      case R.id.quit:
        System.exit(0);
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }

  private final void showAbout()
  {
    final SpannableString aboutInfo=new SpannableString("    Chmod Calculator\n    Version: 1.0\n    Author: Lloyd Dilley\n    lloyd@dilley.me\n    http://www.dilley.me/");
	final AlertDialog.Builder aboutDialog=new AlertDialog.Builder(this);
	final TextView tv=new TextView(this);
	Linkify.addLinks(aboutInfo, Linkify.ALL);
	tv.setMovementMethod(LinkMovementMethod.getInstance());
    tv.setText(aboutInfo);
    tv.setTextColor(Color.WHITE);
    tv.setLinkTextColor(Color.RED);
    aboutDialog.setTitle("About");
    aboutDialog.setIcon(android.R.drawable.ic_dialog_info);
    aboutDialog.setPositiveButton("OK", null);
    aboutDialog.setCancelable(true);
    aboutDialog.setView(tv);
    aboutDialog.create().show();
  }
}
