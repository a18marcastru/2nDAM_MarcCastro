using Mirror;
using UnityEngine;

public class GameNetworkManager : NetworkManager
{
    public GameObject monsterPrefab; // Arrastra el prefab del monstruo en el Inspector

    public override void OnStartServer()
    {
        base.OnStartServer();
        SpawnMonster();
    }

    void SpawnMonster()
    {
        GameObject monster = Instantiate(monsterPrefab, Vector2.zero, Quaternion.identity); // Crea el monstruo en la posición (0,0)
        NetworkServer.Spawn(monster); // Hace que el monstruo exista en la red
    }
}
