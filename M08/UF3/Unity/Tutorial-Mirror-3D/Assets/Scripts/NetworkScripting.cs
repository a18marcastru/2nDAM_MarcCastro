using UnityEngine;
using Mirror;

public class NetworkScripting : NetworkManager
{

    public override void OnClientConnect() {
        base.OnClientConnect();
        Debug.Log("Me conecte al servidor");
    }

    public override void OnServerAddPlayer(NetworkConnectionToClient conn) {
        base.OnServerAddPlayer(conn);
        Debug.Log("Un jugador se ha añadido " + numPlayers);
        MyFirstSyncVar mfs = conn.identity.GetComponent<MyFirstSyncVar>();
        mfs.SetHealthPlayer(10);
        mfs.SetColorPlayer(new Color(Random.Range(0f, 1f), Random.Range(0f, 1f), Random.Range(0f, 1f), 1f));
    }
}
