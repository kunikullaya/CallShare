| Aspect | WCAG 2.0 | WCAG 2.1 | WCAG 2.2 |
|--------|----------|----------|----------|
| Release Date | December 2008 | June 2018 | Expected 2023 |
| Mobile Focus | Limited | Enhanced | Further Enhanced |
| Total Success Criteria | 61 | 78 | 87 |
| New Criteria for iOS | N/A | - 1.3.4 Orientation<br>- 1.4.10 Reflow<br>- 1.4.11 Non-text Contrast<br>- 2.1.4 Character Key Shortcuts<br>- 2.5.1 Pointer Gestures<br>- 2.5.2 Pointer Cancellation<br>- 2.5.3 Label in Name<br>- 2.5.4 Motion Actuation | - 2.4.11 Focus Not Obscured (Minimum)<br>- 2.4.12 Focus Not Obscured (Enhanced)<br>- 2.5.7 Dragging Movements<br>- 2.5.8 Target Size (Minimum)<br>- 3.2.6 Consistent Help<br>- 3.3.7 Redundant Entry<br>- 3.3.8 Accessible Authentication |
| Orientation | Not addressed | Support both portrait and landscape (1.3.4) | Maintained from 2.1 |
| Touch Target Size | Not specified | Indirectly addressed | Minimum size of 24x24 CSS pixels (2.5.8) |
| Drag and Drop | Not addressed | Alternatives to complex gestures (2.5.1) | Alternatives for dragging movements (2.5.7) |
| Text Spacing | Not addressed | No loss of content when adjusting text spacing (1.4.12) | Maintained from 2.1 |
| Focus Handling | Basic | Enhanced | Focus should not be fully obscured (2.4.11, 2.4.12) |
| Authentication | Not specifically addressed | Not specifically addressed | Cognitive function test alternatives required (3.3.8) |
| iOS Example (Orientation) | N/A | `supportedInterfaceOrientations` in view controllers | Maintained from 2.1 |
| iOS Example (Target Size) | N/A | N/A | Use `CGSize(width: 44, height: 44)` for touch targets |
| iOS Example (Drag and Drop) | N/A | Provide button alternatives to swipe gestures | Implement both drag-drop and button-based item movement |
| iOS Example (Authentication) | N/A | N/A | Implement biometric authentication options |
