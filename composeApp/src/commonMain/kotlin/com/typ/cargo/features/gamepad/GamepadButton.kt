package com.typ.cargo.features.gamepad


/**
 * This is an enumerated type for controller buttons.
 *
 * Things are a bit gross here because it needs to correspond with the
 * enum SDL_GameControllerButton in SDL_gamecontroller.h.
 *
 * We skip the invalid button one and the max one (Who would ever want to check those anyway?).
 * This means that we start with an index 0 instead of -1, so that's nice at least.
 *
 * Make sure that the indices of the included buttons matches with the values in the
 * enum in native code. (i.e. that A is 0 in both, B is 1 in both, etc.).
 *
 * @author William Hartman
 */
enum class ControllerButton {
    A,
    B,
    X,
    Y,
    BACK,
    GUIDE,
    START,
    LEFTSTICK,
    RIGHTSTICK,
    LEFTBUMPER,
    RIGHTBUMPER,
    DPAD_UP,
    DPAD_DOWN,
    DPAD_LEFT,
    DPAD_RIGHT,
    BUTTON_MISC1,  /* Xbox Series X share button, PS5 microphone button, Nintendo Switch Pro capture button */
    BUTTON_PADDLE1,  /* Xbox Elite paddle P1 */
    BUTTON_PADDLE2,  /* Xbox Elite paddle P3 */
    BUTTON_PADDLE3,  /* Xbox Elite paddle P2 */
    BUTTON_PADDLE4,  /* Xbox Elite paddle P4 */
    BUTTON_TOUCHPAD,  /* PS4/PS5 touchpad button */
}