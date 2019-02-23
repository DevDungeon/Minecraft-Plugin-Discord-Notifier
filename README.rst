=====================================
Discord notifier plugin for Minecraft
=====================================

Installation
============

- Create a webhook by going to Discord channel -> Edit
- Be sure to set your webhook URL in ``src/main/resources/discord_settings.properties``
- Build it with ``mvn package``
- Copy ``.jar`` file in to the Minecraft server ``plugins/`` directory
- Restart server or ``/reload``
- Done.


Features
========

- Forwards Minecraft chat to Discord chat channel
- Notifies when
  + players join server
  + players die
  + players find diamond or gold


Contact
=======

NanoDano <nanodano@devdungeon.com>