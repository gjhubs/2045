pragma solidity ^0.4.0;

/// @title Empowering.
contract PowerOfAttorney {
    // This declares a new complex type
    // It will represent the power of attorney between Agent and principal.
    struct Empowerment {
        address agent;
        address principal;
        address system;
    }
    mapping(address => Empowerment) public empowerments;

    // Empower msg.sender to principal to
    function empower(address agent, address principal) {
        // store an empowerment: agent to principal authorized by msg.sender
        empowerments[0] = Empowerment(agent, principal, msg.sender);
    }

}
