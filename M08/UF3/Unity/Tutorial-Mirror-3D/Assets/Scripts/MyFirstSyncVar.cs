using UnityEngine;
using Mirror;

public class MyFirstSyncVar : NetworkBehaviour
{
    [SerializeField] private TextMesh nameText;

    [SerializeField] private Renderer renderMaterial;

    [SyncVar(hook = nameof(WhenHealthChange))]
    [SerializeField] private int health;

    [SyncVar(hook = nameof(WhenColorChange))]
    [SerializeField] private Color color;

    #region Server
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

    [Command]
    public void CmdSetHealthPlayer(int health) {
        if(health > 10) return;
        SetHealthPlayer(health);
    }

    [ContextMenu("Cambiar la vida Client")]
    public void SetMyHealth() {
        CmdSetHealthPlayer(8);
    }
    #endregion

    #region Client 
    public void WhenHealthChange(int oldHealth, int newHealth) {
        nameText.text = newHealth.ToString();
    }

    public void WhenColorChange(Color oldColor, Color newColor) {
        renderMaterial.material.SetColor("_BaseColor", newColor);
    }
    #endregion
}
