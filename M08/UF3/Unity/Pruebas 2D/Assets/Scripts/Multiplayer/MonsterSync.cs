using Mirror;
using UnityEngine;

public class MonsterSync : NetworkBehaviour
{
    // Esta funci�n ser� llamada desde el servidor para sincronizar al monstruo con los clientes
    [TargetRpc]
    public void TargetSyncMonster(NetworkConnection target)
    {
        // Sincroniza el monstruo para el cliente al que se le manda
        // Aqu� puedes hacer algo como actualizar la posici�n del monstruo
        Debug.Log("El monstruo ha sido sincronizado para el cliente " + target.connectionId);
    }
}
