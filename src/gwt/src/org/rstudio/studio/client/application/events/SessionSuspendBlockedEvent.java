/*
 * SessionSerializationEvent.java
 *
 * Copyright (C) 2021 by RStudio, PBC
 *
 * Unless you have received this program directly from RStudio pursuant
 * to the terms of a commercial license agreement with RStudio, then
 * this program is licensed to you under the terms of version 3 of the
 * GNU Affero General Public License. This program is distributed WITHOUT
 * ANY EXPRESS OR IMPLIED WARRANTY, INCLUDING THOSE OF NON-INFRINGEMENT,
 * MERCHANTABILITY OR FITNESS FOR A PARTICULAR PURPOSE. Please refer to the
 * AGPL (http://www.gnu.org/licenses/agpl-3.0.txt) for more details.
 *
 */
package org.rstudio.studio.client.application.events;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class SessionSuspendBlockedEvent extends GwtEvent<SessionSuspendBlockedEvent.Handler>
{
   public static class Data extends JavaScriptObject
   {
      protected Data() {}

      public final native Boolean isEmpty() /*-{
         return Object.keys(this).length === 0;
      }-*/;

      public final native String getMsg() /*-{
         let msg = 'Session unable to suspend:\n';

         if (this.hasOwnProperty('active-child-process'))
             msg += 'A child process is running\n';
         if (this.hasOwnProperty('executing'))
             msg += 'R is executing\n';
         if (this.hasOwnProperty('active-connection'))
             msg += 'A connection is active\n';
         if (this.hasOwnProperty('overlay'))
             msg += '\n';
         if (this.hasOwnProperty('active-external-pointer'))
             msg += '\n';
         if (this.hasOwnProperty('active-job'))
             msg += '\n';
         if (this.hasOwnProperty('incomplete-command-prompt'))
             msg += '\n';
         if (this.hasOwnProperty('edit-completion'))
             msg += '\n';
         if (this.hasOwnProperty('choose-file-completion'))
             msg += '\n';
         if (this.hasOwnProperty('locator-completion'))
             msg += '\n';
         if (this.hasOwnProperty('unsaved-handler-completion'))
             msg += '\n';
         if (this.hasOwnProperty('user-prompt-completion'))
             msg += '\n';
      }-*/;

      protected String getOverlay() {
         return String(); // override for overlay explaination
      };
   }
   public static final Type<Handler> TYPE = new Type<>();

   public SessionSuspendBlockedEvent(Data data)
   {
      data_ = data;
   }

   public Data getBlocking()
   {
      return data_;
   }

   @Override
   protected void dispatch(Handler handler)
   {
      handler.onSessionSuspendBlocked(this);
   }

   @Override
   public GwtEvent.Type<Handler> getAssociatedType()
   {
      return TYPE;
   }

   private final Data data_;

   public interface Handler extends EventHandler
   {
      void onSessionSuspendBlocked(SessionSuspendBlockedEvent event);
   }
}
