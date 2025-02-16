using UnityEngine;
using Mirror;

public class MonsterController : NetworkBehaviour
{
    public float speed = 3f; // Velocidad del monstruo
    private Transform target;

    void Update()
    {
        if (!isServer) return; // Solo el servidor controla el monstruo

        FindClosestPlayer();
        if (target != null)
        {
            Vector2 newPosition = Vector2.MoveTowards(transform.position, target.position, speed * Time.deltaTime);
            RpcMoveMonster(newPosition); // Enviar la nueva posición a los clientes
        }
    }

    [Server]
    void FindClosestPlayer()
    {
        GameObject[] players = GameObject.FindGameObjectsWithTag("Player");
        float minDistance = Mathf.Infinity;
        Transform closestPlayer = null;

        foreach (GameObject player in players)
        {
            float distance = Vector2.Distance(transform.position, player.transform.position);
            if (distance < minDistance)
            {
                minDistance = distance;
                closestPlayer = player.transform;
            }
        }

        target = closestPlayer;
    }

    [ClientRpc]
    void RpcMoveMonster(Vector2 newPosition)
    {
        transform.position = newPosition;
    }
}
