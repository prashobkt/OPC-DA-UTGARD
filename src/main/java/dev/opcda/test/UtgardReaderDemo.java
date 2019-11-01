	package dev.opcda.test;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openscada.opc.lib.common.ConnectionInformation;
import org.openscada.opc.lib.da.AccessBase;
import org.openscada.opc.lib.da.AutoReconnectController;
import org.openscada.opc.lib.da.DataCallback;
import org.openscada.opc.lib.da.Item;
import org.openscada.opc.lib.da.ItemState;
import org.openscada.opc.lib.da.Server;
import org.openscada.opc.lib.da.SyncAccess;

public class UtgardReaderDemo {
  /**
   * Main application, arguments are provided as system properties, e.g.<br>
   * java -Dhost="localhost" -Duser="admin" -Dpassword="secret" -jar demo.opc.jar<br>
   * Tested with a windows user having administrator rights<br>
   * @param args unused
   * @throws Exception in case of unexpected error
   */
  public static void main(String[] args) throws Exception {
    Logger.getLogger("org.jinterop").setLevel(Level.ALL); // Quiet => Level.OFF

    final String host = "devcomp";
    final String user = "prashob";
    final String password = "123";
    // Powershell: Get-ItemPropertyValue 'Registry::HKCR\Matrikon.OPC.Simulation.1\CLSID' '(default)'
    final String clsId = System.getProperty("clsId", "F8582CF2-88FB-11D0-B850-00C0F0104305");
    final String itemId = System.getProperty("itemId", "Saw-toothed Waves.Int2");

    final ConnectionInformation ci = new ConnectionInformation(user, password);
    ci.setHost(host);
    ci.setClsid("F8582CF2-88FB-11D0-B850-00C0F0104305");
    ci.setDomain("devcomp");
    ci.setProgId("Matrikon.OPC.Simulation.1");
    final Server server = new Server(ci, Executors.newSingleThreadScheduledExecutor());
    AutoReconnectController autoReconnectController= new AutoReconnectController(server, 6000);
    autoReconnectController.connect();

    final AccessBase access = new SyncAccess(server, 1000);
    access.addItem(itemId, new DataCallback() {
      public void changed(final Item item, final ItemState state) {
        System.out.println(state);
      }
    });https://support.apple.com/iphone-7-no-service

    access.bind();
    Thread.sleep(10000);
    access.unbind();
  }
}