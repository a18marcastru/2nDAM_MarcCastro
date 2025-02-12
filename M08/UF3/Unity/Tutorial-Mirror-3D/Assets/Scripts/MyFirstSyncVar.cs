using UnityEngine;
using Mirror;

public class MyFirstSyncVar : NetworkBehaviour
{
    [SyncVar]
    [SerializeField] private int health;

    [SyncVar]
    [SerializeField] private Color color;

    [Server]
    public void SetHealthPlayer(int health)
    {
        this.health = health;
    }

    [Server]
    public void SetColorPlayer(Color color)
    {
        this.color = color;
    }
}
