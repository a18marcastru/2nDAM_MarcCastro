using UnityEngine;

public class PlayerController : FSM
{
    public enum FSMState {
        None, Idle, Moving, Jump, Fall, Attack, Hit, Dead
    }

    public FSMState curState = FSMState.None;

    [SerializeField] Animator animator;
    [SerializeField] Rigidbody2D rb2d = null;
    [SerializeField] Transform attackPosOrigin;

    [SerializeField] float speed = 400.0f;
    [SerializeField] float jumpForce = 20.0f;
    [SerializeField] float attackRadius = 1.0f;
    [SerializeField] bool isPlayerDead = false;

    [Header("Ground Checker")]
    [SerializeField] float distance = 1;
    [SerializeField] LayerMask layerMask;

    private float horizontalAxis = 0;
    private bool jump = false;
    private bool attack = false;

    protected override void Initialize() {
        Invoke("SetDefaultState", 3f);
    }

    private void SetDefaultState() {
        curState = FSMState.Idle;
    }
}
